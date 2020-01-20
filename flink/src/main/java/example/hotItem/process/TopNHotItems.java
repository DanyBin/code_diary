package example.hotItem.process;

import example.hotItem.window.WindowResultFuntion;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName TopNHotItems
 * @Author bin
 * @Date 2019/12/24 上午11:08
 * @Decr TODO
 *  求某个窗口中前 N 名的热门点击商品，key 为窗口时间戳，输出为 TopN 的结果字符串
 * @Link TODO
 **/
public class TopNHotItems extends KeyedProcessFunction<Tuple,WindowResultFuntion.ItemViewCount,String> {

    private final int topSize;

    public TopNHotItems(int topSize) {
        this.topSize = topSize;
    }

    // 用于存储商品与点击数的状态，待收齐同一个窗口的数据后，再触发 TopN 计算
    private ListState<WindowResultFuntion.ItemViewCount> itemState;


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        // 状态的注册
        ListStateDescriptor<WindowResultFuntion.ItemViewCount> itemsStateDesc = new ListStateDescriptor<>(
                "itemState-state",
                WindowResultFuntion.ItemViewCount.class
        );
        itemState = getRuntimeContext().getListState(itemsStateDesc);
    }




    @Override
    public void processElement(WindowResultFuntion.ItemViewCount value, Context ctx, Collector<String> out) throws Exception {

        //每条数据都保存到状态中
        itemState.add(value);

        //注册 windowEnd+1 的 EventTime Timer, 当触发时，说明收齐了属于windowEnd窗口的所有商品数据
        ctx.timerService().registerEventTimeTimer(value.windowEnd +1);
    }


    //触发定时器的操作
    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {

        // 获取收到的所有商品点击量
        ArrayList<WindowResultFuntion.ItemViewCount> allItems = new ArrayList<>();
        for(WindowResultFuntion.ItemViewCount item: itemState.get()){
            allItems.add(item);
        }

        // 提前清除状态中的数据，释放空间
        itemState.clear();

        // 按照点击量从大到小排序
        allItems.sort(new Comparator<WindowResultFuntion.ItemViewCount>() {
            @Override
            public int compare(WindowResultFuntion.ItemViewCount o1, WindowResultFuntion.ItemViewCount o2) {
                return (int) (o2.viewCount - o1.viewCount);
            }
        });

        // 将排名信息格式化成 String, 便于打印
        StringBuilder result = new StringBuilder();
        result.append("====================================\n");
        result.append("时间: ").append(new Timestamp(timestamp-1)).append("\n");
        for (int i=0;i<topSize;i++) {
            WindowResultFuntion.ItemViewCount currentItem = allItems.get(i);
            // No1:  商品ID=12224  浏览量=2413
            result.append("No").append(i).append(":")
                    .append("  商品ID=").append(currentItem.itemId)
                    .append("  浏览量=").append(currentItem.viewCount)
                    .append("\n");
        }
        result.append("====================================\n\n");

        out.collect(result.toString());
    }
}
