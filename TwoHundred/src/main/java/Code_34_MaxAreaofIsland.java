/**
 * 给定一个包含了一些 0 和 1 的非空二维数组grid 。
 * <p>
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_34_MaxAreaofIsland {

  private int n;
  private int m;
  int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int maxAreaOfIsland(int[][] grid) {
    if (null == grid || grid.length == 0) {
      return 0;
    }
    n = grid.length;
    m = grid[0].length;
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        //每个节点都遍历一次
        maxArea = Math.max(maxArea,dfs(grid,i,j));
      }
    }
    return maxArea;
  }

  /**
   * 深度优先搜索
   * 遍历节点 - 走到头
   * @return
   */
  public int dfs(int[][] grid, int r, int c) {
    if (r >= n || c>= m || grid[r][c] == 0 || r < 0 || c < 0) {
      return 0;
    }

    grid[r][c] = 0; //标记走过的节点
    int area = 1;
    //向四个方面走
    for (int[] d : direction) {
      area += dfs(grid, r + d[0], c + d[1]);
    }
    return area;
  }
}
