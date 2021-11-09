/**
 * 统计所有小于非负整数 n 的质数的数量。
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class Code_68_CountPrimes {

  /**
   * 由于质数 是除了 1 之外，对其它自然数取余都是为1
   * @return
   */
  public int countPrimes(int n) {
    int count = 0;
    for (int i = 2; i < n; i++) {
      boolean flag = true;

      //与i之前的所有元素做取余操作
      for (int j = 2; j < i; j++) {
        //余数为0的情况，表示并不是素数
        if (i % j == 0) {
          flag = false;
          break;
        }
      }

      //计算质数
      if (flag) {
        count++;
      }
    }
    return count;
  }


  /**
   * 采用埃拉托斯特尼筛法。
   * 每次排除都排除当前元素的倍数
   * @param n
   * @return
   */
  public int countPrimes2(int n) {
    int count = 0;
    boolean[] visit = new boolean[n];
    for (int i = 2; i < n; i++) {
      //已经被访问过
      if (visit[i]) {
        continue;
      }
      count++;

      //注意j 是i的倍数。所有i的倍数，都不是质数
      for (int j = i * i; j < n; j+=i) {
        visit[j] = true;
      }
    }
    return count;
  }



}
