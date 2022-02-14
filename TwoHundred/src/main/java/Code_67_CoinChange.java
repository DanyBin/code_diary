/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class Code_67_CoinChange {
  public int coinChange(int[] coins,int amount) {
    int[] dp = new int[amount+1];
    for (int n : coins) {
      //注意，重复背包时，背包的时由 小 -> 大
      for (int i = 0; i <= amount; i++) {
        if (i == n ) {
          dp[i] = 1;
        } else if (dp[i] == 0 && dp[i-n] != 0) {
          dp[i] = dp[i-n] + 1;
        } else {
          dp[i] = Math.min(dp[i],dp[i-n] + 1);
        }
      }
    }
    return dp[amount] == 0 ? -1 : dp[amount];
  }
}