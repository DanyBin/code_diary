/**
 *一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 动态规划，每次都依赖前一次的结果。
 * 解题思路 ：
 *    1。 通过新建数组 - 每个值都由前一个值-转化而来
 *    2。 遍历整个数据，当到达最后后一位时，也就是获取了最大值
 */
public class Code_59_DecodeWays {

  public int numDecodings(String s){
    int n = s.length();
    int[] dp = new int[n+1];
    dp[0] = 1;
    for (int i=1 ;i <= n; i++) {
      //如果不等于0 则代表是一个整数
      if (s.charAt(i - 1) != '0') {
        dp[i] = dp[i] + dp[i-1];
      }

      //考虑两个字符的情况
      if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0') <= 26)) {
        dp[i] = dp[i] + dp[i-2];
      }
    }
    return dp[n];
  }
}
