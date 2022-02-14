import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
 *
 * 解题思路
 *  1。 新增一个数据，每个值又是由前一值转化而来
 *  2. 遍历数组 --
 */
public class Code_60_LengthOfLIS {
  public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length + 1];
    //初始化一个
    dp[0] = 1;
    int maxans = 1;
    for (int i = 1; i <= nums.length ; i++) {
      //注意此处遍历是， 当前元素之前的
      for (int j = 0; j < i; j++ ) {
        //大于之前的值
        if (nums[i] > nums[j]) {
          //由上一个元素转化的结果
          dp[i] = Math.max(dp[i],dp[i] + 1);
        }
        maxans = Math.max(maxans,dp[i]);
      }
    }
    return maxans;
  }
}

