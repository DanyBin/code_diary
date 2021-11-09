/**
 *
 * 你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class Code_35_NumberOfIslands {
  static int n;
  static int m;
  //四个方向
  static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
  public static int numIslands(char[][] grid) {
    if (null == grid || grid.length == 0) {
      return 0;
    }
    n = grid.length;
    m = grid[0].length;
    int num = 0;
    for (int i=0 ;i < n; i ++) {
      for (int j=0; j < m; j ++) {
        if (grid[i][j] != '0') {
          //标记已经走过的节点。防止1的节点，重新走一遍
          dfs(grid,i,j);
          num++;
        }
      }
    }
    return num;
  }

  public static void dfs(char[][] grid, int r ,int c) {
    //终止条件
    if (r >= n || c >= m || grid[r][c] == '0' || r < 0 || c < 0) {
      return;
    }
    grid[r][c] = '0'; //标记走过的节点
    //向四个方面走 -- 深度遍历下一个节点。直到头
    for (int[] d : direction) {
      dfs(grid,r + d[0], c + d[1]);
    }
  }
}
