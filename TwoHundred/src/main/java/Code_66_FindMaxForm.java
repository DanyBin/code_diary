/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 */
public class Code_66_FindMaxForm {
  public int findMaxForm(String[] strs,int m,int n) {
    int[][] dp = new int[m+1][n+1];
    for (String s : strs) {
      //每个字符串仅使用一次
      int zero = 0;
      int one = 0;

      //计算单个字符串的 0 与 1
      for (char c : s.toCharArray()) {
        if (c == '0') {
          zero++;
        } else {
          one ++;
        }
      }

      //开始背包.先处理m的背包。注意 i=m。从背包的最大值开始
      for (int i = m; i >= zero; i --) {
        //处理n的背包
        for (int j = n; j >= one; j--) {
          //比较大小
          dp[i][j] = Math.max(dp[i][j],dp[i-zero][j-one] + 1);
        }
      }
    }
    return dp[m][n];
  }
}