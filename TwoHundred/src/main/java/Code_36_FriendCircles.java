/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * <p>
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 */
public class Code_36_FriendCircles {

  int n;
  int m;
  //向右 ，向下
  int[][] direction = {{0, 1}, {0, 1},{1,0},{-1,0}};

  public static void main(String[] args) {
    final Code_36_FriendCircles t = new Code_36_FriendCircles();
    int[][] isConnected = {{1,0,0},{0,1,0},{0,0,1}};
    final int circleNum = t.findCircleNum(isConnected);
    System.out.println(circleNum);
  }

  public int findCircleNum(int[][] isConnected) {
    if (null == isConnected || isConnected.length == 0) {
      return 0;
    }
    n = isConnected.length;
    m = isConnected[0].length;

    int nums = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (isConnected[i][j] != 0) {
          //标记走过的节点
          dfs(isConnected,i,j);
          nums++;
        }
      }
    }
    return nums;
  }

  public void dfs(int[][] grid, int i, int j) {
    if (i >= n || j >= m ||  i < 0 || j < 0 || grid[i][j] == 0 ) {
      return;
    }
    //标记节点
    grid[i][j] = 0;
    for (int[] d : direction) {
      dfs(grid,i + d[0] , j + d[1]);
    }
  }
}
