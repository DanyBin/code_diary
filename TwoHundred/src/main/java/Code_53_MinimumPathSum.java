import com.sun.jmx.remote.internal.ArrayQueue;

/**
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 * 求最短路径 -- 动态规划
 */
public class Code_53_MinimumPathSum {
  //向下 或 向右走
  int[][] borad = {{1,0},{0,1}};

  public static void main(String[] args) {
    final Code_53_MinimumPathSum code_53_minimumPathSum = new Code_53_MinimumPathSum();
    final int i = code_53_minimumPathSum.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
    System.out.println(i);
  }

  public int minPathSum(int[][] grid) {
    int row = grid.length;
    int columns = grid[0].length;
    int[][] dp = new int[row][columns];
    //边界条件考虑 -- row 纵轴考虑
    for (int i = 1; i < row; i++) {
      dp[i][0] = dp[i-1][0] + grid[i][0];
    }

    //边界条件考虑 -- 横轴考虑
    for (int i = 1; i < columns; i++) {
      dp[0][i] = dp[0][i-1] + grid[0][i];
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < columns; j++) {
        dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
      }
    }
    return dp[row-1][columns-1];
  }
}
