/**
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class Code_20_MaximumSubarray {
  int maxSubArray(int[] nums) {
    if (null == nums || nums.length == 0) {
      return 0;
    }
    int prev = nums[0];
    int maxValue = prev;
    for (int i = 1; i< nums.length ; i ++) {
      prev = prev > 0 ? prev + nums[i] : nums[i];
      maxValue = Math.max(maxValue,prev);
    }
    return maxValue;
  }
}
