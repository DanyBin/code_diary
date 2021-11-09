/**
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。
 * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code_37_SurroundedRegions {
  int n;
  int m;
  int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
  public void solve(char[][] board) {
    if (null == board || board.length == 0) {
      return;
    }
    n = board.length;
    m = board[0].length;
    for (int i=0 ;i < n; i ++) {
      for (int j=0;j < m; j ++) {
        dfs(board,i,j);
      }
    }
  }

  private void dfs(char[][] board,int i,int j) {
    //边界条件
    if (i >= n - 1 || j >= m - 1 || i <= 0 || j <= 0 || board[i][j] == 'x') {
      return;
    }
    for (int[] d : direction) {
      int x = i + d[0];
      int y = j + d[1];
      //相临节点（提前判断下一个节点）为边界
      if (x == 0 || y == 0 || x == n - 1 || y == m - 1) {
        //相邻节点为'o'.直接返回
        if (board[x][y] == 'o') {
         return;
        }
      }
      dfs(board,i + d[0], j + d[1]);
    }
  }
}
