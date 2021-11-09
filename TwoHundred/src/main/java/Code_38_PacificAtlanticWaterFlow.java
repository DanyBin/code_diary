import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *
 *
 * 示例：
 *给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_38_PacificAtlanticWaterFlow {

  /**
   * 遍历节点时，增加限制条件
   *
   * @param matrix
   * @return
   */
  int n;
  int m;
  int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  private int[][] matrix;

  public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    if (null == matrix || matrix.length == 0) {
      return Collections.emptyList();
    }
    n = matrix.length;
    m = matrix[0].length;

    //记录走过的节点
    boolean[][] canReachA = new boolean[n][m];
    boolean[][] canReachB = new boolean[n][m];
    this.matrix = matrix;

    //优先把边界中的元素走完
    for (int i = 0; i < m;i++) {
      dfs(i,0,canReachA);
      dfs(i,n-1,canReachB);
    }

    for (int i = 0; i< n;i ++) {
      dfs(0,i,canReachA);
      dfs(m-1,i,canReachB);
    }

    List<List<Integer>> ret = Lists.newArrayList();
    for (int i=0 ; i < n; i++) {
      for (int j=0 ; j < m; j++) {
        //证明到达过边界
        if (canReachA[i][j] && canReachB[i][j]) {
          ret.add(Arrays.asList(i,j));
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  private void dfs(int i,int j,boolean[][] canReach) {
    //终止条件
    if(canReach[i][j]) {
      return;
    }
    //标记
    canReach[i][j] = true;
    for (int[] d : direction) {
      int nextA = d[0] + i;
      int nextB = d[1] + j;
      //循环终止条件 -- 边界 & cur < next
      if (nextA < 0 || nextB < 0 || nextA >= n || nextB >= m ||
          matrix[i][j] > matrix[nextA][nextB]) {
        continue;
      }
      //否则继续递归
      dfs(nextA,nextB,canReach);
    }
  }
}

