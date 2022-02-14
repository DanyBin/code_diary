import java.util.concurrent.CountDownLatch;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 *
 * 是否为等差数列
 * A[i] - A[i-1] = A[i+1] - A[i] 时。
 * dp[i] = dp[i-1] + 1
 *
 */
public class Code_56_ArithmeticSlices {

  public int numberOfArithmeticSlices(int[] nums) {
    int[] dp = new int[nums.length];
    for (int i= 2;i < nums.length ; i++) {
      //3个元素时，是否为等差数列
      if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
        //状态变化
        dp[i] = dp[i-1] + 1;
      }
    }

    int cnt = 0;
    for (int k : dp) {
      cnt += k;
    }
    return cnt;
  }
}

