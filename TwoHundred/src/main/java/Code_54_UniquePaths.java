import java.util.Arrays;

/**
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：m = 3, n = 7
 * 输出：28
 *
 */
public class Code_54_UniquePaths {
  public int uniquePaths(int m,int n) {
    int[] dp = new int[n];
    Arrays.fill(dp,1);
    //遍历
    for (int i = 1; i< m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] = dp[j] + dp[j-1];
      }
    }
    return dp[n-1];
  }
}
