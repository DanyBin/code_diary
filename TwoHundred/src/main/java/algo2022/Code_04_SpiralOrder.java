package algo2022;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 */
public class Code_04_SpiralOrder {


  public static void main(String[] args) {
    spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
  }
  public static void spiralOrder(int[][] matrix) {
    //左边界
    int t1 = 0;
    //右边界
    int t2 = matrix[0].length - 1;
    //上边界
    int t3 = 0;
    //下边界
    int t4 = matrix.length - 1;
    List<Integer> ret = new ArrayList<>();

    while (true) {
      //左边界 -> 右边界
      for (int i = t1; i <= t2;i ++) {
        ret.add(matrix[t3][i]);//t3为上边界不变, 左边界循环
      }

      if (++t3 > t4) {
        break;
      }
      //上边界 -> 下边界
      for (int i = t3; i <= t4; i++) {
        ret.add(matrix[i][t2]); //t2 为右边界不变，上边界++
      }


      if (t1 > --t2) {
        break;
      }
      //右边界 -> 左边界
      for(int i = t2; i >= t1; i--) {
        ret.add(matrix[t4][i]);//下边界不变，右边界--
      }

      if (t3 > --t4) {
        break;
      }
      //下边界 -> 上边界
      for (int i = t4; i >= t3 ;i --) {
        ret.add(matrix[i][t1]); //左边界不变，下边界--
      }

      if (++t1 > t2) {
        break;
      }
    }
    System.out.println(ret);
  }
}
