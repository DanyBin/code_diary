/**
 * 给出n个数对。在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当b < c时，数对(c, d)才可以跟在(a, b)后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-pair-chain
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 */
public class Code_61_FindLongestChain {

  public int findLongestChain(int[][] pairs) {
    int[] dp = new int[pairs.length + 1];
    dp[0] = 1;
    int max = 1;
    for (int i = 1; i <= pairs.length; i++) {
      //注意遍历当前元素之前的元素
      for (int j = 0; j < i;j ++) {
        //判断条件 - 比较当前元素 与 之前的元素的
        if (pairs[i][1] < pairs[j][1]) {
          //计算前一个变量 + 当前变量的 最大值
          dp[i] = Math.max(dp[i],dp[i] + 1);
        }
      }
      max = Math.max(dp[i],max);
    }
    return max;
  }
}
