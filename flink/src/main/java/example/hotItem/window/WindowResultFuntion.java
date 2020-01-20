package example.hotItem.window;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @ClassName WindowResultFuntion
 * @Author bin
 * @Date 2019/12/24 上午10:55
 * @Decr TODO
 * @Link TODO
 **/
public class WindowResultFuntion  implements WindowFunction<Long, WindowResultFuntion.ItemViewCount, Tuple, TimeWindow> {


    @Override
    public void apply(
            Tuple tuple, // 窗口主键。即itemId
            TimeWindow window, //窗口
            Iterable<Long> aggregateResult, //聚会函数的结果。即count值
            Collector<ItemViewCount> out //输出类型
    ) throws Exception {
        Long itemId = ((Tuple1<Long>)tuple).f0;
        Long count  = aggregateResult.iterator().next();
        out.collect(ItemViewCount.of(itemId,window.getEnd(),count));
    }

    /** 商品点击量(窗口操作的输出类型) */
    public static class ItemViewCount {
        public long itemId;     // 商品ID
        public long windowEnd;  // 窗口结束时间戳
        public long viewCount;  // 商品的点击量

        public static ItemViewCount of(long itemId, long windowEnd, long viewCount) {
            ItemViewCount result = new ItemViewCount();
            result.itemId = itemId;
            result.windowEnd = windowEnd;
            result.viewCount = viewCount;
            return result;
        }
    }
}
