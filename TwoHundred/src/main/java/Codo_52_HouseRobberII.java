/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
 * 系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 比较两种情况  (0,n - 2) 与 (1,n-1)
 * 每种情况都不需要考虑首尾相邻
 * Max([i-2] + [i],[i-1])
 *
 */
public class Codo_52_HouseRobberII {


  public int rob(int[] nums) {
   if (nums == nums || nums.length == 0) {
     return 0;
   }
   if (nums.length == 1) {
     return nums[0];
   }
   return Math.max(rob(nums,0,nums.length - 2),rob(nums,1,nums.length - 1));
  }

  public int rob(int[] nums,int first,int last) {
    int p1 = 0, p2 =0;
    for (int i = first; i <=last - 1;i ++) {
      int cur = Math.max(p1 + nums[i],p2);
      p1 = p2;
      p2 = cur;
    }
    return p2;
  }
}
