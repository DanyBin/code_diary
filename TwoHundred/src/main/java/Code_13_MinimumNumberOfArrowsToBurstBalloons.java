import java.util.Arrays;
import java.util.Comparator;

/**
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * Output:
 * 2
 * 题目描述：气球在一个水平数轴上摆放，可以重叠，飞镖垂直投向坐标轴，使得路径上的气球都被刺破。求解最小的投飞镖次数使所有气球都被刺破。
 *
 * 也是计算不重叠的区间个数，不过和 Non-overlapping Intervals 的区别在于，[1, 2] 和 [2, 3] 在本题中算是重叠区间。
 */
public class Code_13_MinimumNumberOfArrowsToBurstBalloons {

  public static int findMinArrowShots(int[][] points) {
    if (points.length == 0) {
      return 0;
    }
    //先排序
    Arrays.sort(points, Comparator.comparing(o -> o[1]));
    int cnt = 1;
    int end = points[0][1];
    for (int i=1; i < points.length ;i ++) {
      //算重叠的情况
      if (points[i][0] <= end) {
        continue;
      }
      end = points[i][1];
      cnt++;
    }
    return points.length - cnt;
  }
}
