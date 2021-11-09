import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_58_PerfectSquares {


  public int numSquares(int n) {
    List<Integer> squareList = generateSquareList(n);
    int[] dp = new int[n + 1];
    for (int i = 1; i<= n ; i ++) {
      int min = Integer.MAX_VALUE;
      //遍历所有的平方数
      for (int square : squareList) {
        //获取小于当前元素
        if (square > i) {
          break;
        }
        //dp数组中保存，当前元素对应的最小值
        min = Math.min(min,dp[i-square]+1);
      }
      dp[i] = min;
    }
    return dp[n];
  }


  /**
   * 生成平方数
   * @param n
   * @return
   */
  public List<Integer> generateSquareList(int n) {
    List<Integer> squareList = new ArrayList<>();
    int diff = 3;
    int squre = 1;
    while (squre <= n) {
      squareList.add(squre);
      squre += diff;
      diff+= 2;
    }
    return squareList;
  }
}

