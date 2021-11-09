/**
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_65_FindTargetSumWays {
  public int findTargetSumWays(int[] nums,int target) {
    return dfs(nums,0,target);
  }
  public int dfs(int[] nums,int start,int target) {
    //递归结束条件
    if (start == nums.length) {
      return target == 0 ? 1 : 0;
    }
    return dfs(nums,start + 1,target + nums[start]) +
        dfs(nums,start + 1,target - nums[start]);
  }

  /**
   * 是否相等 == (target + sum(num))/ 2 == 0
   * @param nums
   * @param target
   * @return
   */
  public int findTargetSumWays2(int[] nums,int target) {
    final int sum = computeSum(nums);
    //只有为偶数的时候，才会出现两个数 相等
    if (sum < target || (sum + target) %  2 == 1) {
      return 0;
    }
    //求背包的重量，也就是中间值
    int W = (target + sum) / 2;
    int[] dp = new int[W + 1];
    dp[0] = 1;
    for (int n : nums) {
      //逆序遍历 -- i ==W 从背包的重量开始
      for (int i = W; i >= n; i--) {
        dp[i] = dp[i] + dp[i-n];
      }
    }
    return dp[W];
  }
  public int computeSum(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return sum;
  }
}
