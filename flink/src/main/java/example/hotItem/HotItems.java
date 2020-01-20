package example.hotItem;

import example.hotItem.aggr.CountAgg;
import example.hotItem.pojo.UserBehavior;
import example.hotItem.process.TopNHotItems;
import example.hotItem.window.WindowResultFuntion;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.io.PojoCsvInputFormat;
import org.apache.flink.api.java.typeutils.PojoTypeInfo;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.io.File;
import java.net.URL;

/**
 * @ClassName HotItems
 * @Author bin
 * @Date 2019/12/24 上午10:19
 * @Decr TODO
 *      topn 3 的热门商家点击量
 *      原始数据=数据集的每一行表示一条用户行为，由用户ID、商品ID、商品类目ID、行为类型和时间戳组成，并以逗号分隔。
 * @Link TODO
 **/
public class HotItems {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //设置并发1，控制打印结果顺序
        env.setParallelism(1);

        //读取csv
        URL resource = HotItems.class.getClassLoader().getResource("UserBehavior.csv");
        Path filePath = Path.fromLocalFile(new File(resource.toURI()));

        // 抽取 UserBehavior 的 TypeInformation，是一个 PojoTypeInfo
        PojoTypeInfo<UserBehavior> pojoType = (PojoTypeInfo<UserBehavior>)TypeExtractor.createTypeInfo(UserBehavior.class);

        // 由于 Java 反射抽取出的字段顺序是不确定的，需要显式指定下文件中字段的顺序
        String[] fieldOrder = {"userId", "itemId", "categoryId", "behavior", "timestamp"};

        // 创建 PojoCsvInputFormat
        PojoCsvInputFormat<UserBehavior> csvInput = new PojoCsvInputFormat<>(filePath, pojoType, fieldOrder);

        //创建输入源
        DataStream<UserBehavior> dataSource = env.createInput(csvInput,pojoType);

        //设置EventTime
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


        //EventTime 与 Watermark
        DataStream<UserBehavior> timeData = dataSource
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<UserBehavior>() {
                    @Override
                    public long extractAscendingTimestamp(UserBehavior userBehavior) {
                        // 原始数据单位秒，将其转成毫秒
                        return userBehavior.timestamp * 1000;
                    }
                });

       //过滤点击事件
        DataStream<UserBehavior> pvData = timeData
                .filter(new FilterFunction<UserBehavior>() {
                    @Override
                    public boolean filter(UserBehavior userBehavior) throws Exception {
                        // 过滤出只有点击的数据
                        return userBehavior.behavior.equals("pv");
                    }
                });

        //窗口统计点击量
        DataStream<WindowResultFuntion.ItemViewCount> windowData = pvData
                .keyBy("itemId")
                .timeWindow(Time.minutes(60), Time.minutes(5)) //每5分钟统计「最近一小时」的点击量 （1小时窗口，5分钟滑动一次）
                .aggregate(new CountAgg(), new WindowResultFuntion());

        //TopN 计算最热门商品
        DataStream<String> topItems = windowData
                .keyBy("windowEnd")
                .process(new TopNHotItems(3));

        topItems.print();
        env.execute("Hot Items job");

    }

}
