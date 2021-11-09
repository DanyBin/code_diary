/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 *
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 *
 */
public class Code_70_TrailingZeroes {

  /**
   *  2*5 = 10
   *  计算包含5的个数就行
   * @param n
   * @return
   */
  public int trailingZeroes(int n) {
    return n == 0 ? 0 : n/5 + trailingZeroes(n/5);
  }


}
