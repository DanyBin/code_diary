import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 0 - 1 背包问题
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 */
public class Code_64_CanPartition {

  public boolean canPartition(int[] nums) {
    //计算总数
    final int sum = computeSum(nums);
    //肯定为偶数，才能相等
    if (sum % 2 != 0 ) {
      return false;
    }
    int W = sum / 2; //重量（容量）
    //创建一个背包容量的数组
    int[] dp = new int[W+1];
    for (int i = 0; i< nums.length; i ++) {
      //主要是遍历背包的大 -> 小
      for (int j = W; j >= nums[i]; j--) {
        //比较大小。dp[i - nums[i]] == 就是当前剩余的背包容量
        //注意dp[j] 存储的值是 num[i] 的值
        dp[j] = Math.max(dp[j],dp[j-nums[i]] + nums[i]);
      }
    }
   if (dp[W] == W) {
      return true;
   }
   return false;
  }
  public int computeSum(int[] nums) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }
    return sum;
  }
}
