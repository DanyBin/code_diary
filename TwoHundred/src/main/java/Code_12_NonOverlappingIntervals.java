import java.util.Arrays;
import java.util.Comparator;

/**
 * Input: [ [1,2], [1,2], [1,2] ]
 *
 * Output: 2
 *
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 *
 * Input: [ [1,2], [2,3] ]
 *
 * Output: 0
 *
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 *给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class Code_12_NonOverlappingIntervals {

  public static int eraseOverlapIntervals(int[][] intervals) {
    //按照第一个元素排序
    Arrays.sort(intervals,Comparator.comparing(o -> o[1]));
    //计算重复值
    int cnt = 1;
    int end = intervals[0][1];

    for (int i = 1; i < intervals.length ; i ++) {
      //数据对比，按照第一个元素
      if (intervals[i][0] < end) {
        continue;
      }
      //重新更新end
      end = intervals[i][1];
      cnt++;
    }
    return intervals.length - cnt;
  }
}
