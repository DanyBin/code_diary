package example.wordCount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @ClassName SocketWindowWordCount
 * @Author bin
 * @Date 2019/12/23 上午11:06
 * @Decr TODO
 *         每5秒聚合一次单词数
 *         term - nc -lk 9000
 * @Link TODO
 **/
public class SocketWindowWordCount {

    public static void main(String[] args)throws Exception {

        /*-----初始化------*/
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> stream = env.socketTextStream("localhost", 9999, "\n");

        /*-----算子使用-----*/
        DataStream<Tuple2<String, Integer>> wordSum = stream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> out) throws Exception {
                for (String word : s.split("\\s")) {
                    out.collect(Tuple2.of(word, 1));
                }
            }
        })
                .keyBy(0)  //使用tuple.fo 分组
                .timeWindow(Time.seconds(5))
                .sum(1);

        wordSum.print().setParallelism(1);

        /*-------启动任务---------*/
        env.execute("sock word count");
    }
}
