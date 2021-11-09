/**
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * [i]抢劫最大的金额 = Max([i-2] + [i] , [i-1])
 */
public class Codo_52_HouseRobber {
  public static void main(String[] args) {
    final Codo_52_HouseRobber codo_52_houseRobber = new Codo_52_HouseRobber();
    final int rob = codo_52_houseRobber.rob(new int[]{2, 7, 9, 3, 1});
    System.out.println(rob);
  }
  public int rob(int[] nums) {
    int p1 = 0,p2 = 0;
    for (int i = 0 ; i < nums.length; i ++) {
      final int cur = Math.max(p1 + nums[i], p2);
      p1 = p2;
      p2 = cur;
    }
    return p2;
  }
}
