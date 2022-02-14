/**
 *
 *假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 动态规划的思想 - 每一步的结果都是依赖上一步的结果
 */
public class Code_52_ClimbingStairs {
  public static void main(String[] args) {
    final Code_52_ClimbingStairs code_52_climbingStairs = new Code_52_ClimbingStairs();
    final int i = code_52_climbingStairs.climbStairs(4);
    System.out.println(i);
  }
    public int climbStairs(int n) {
      if (n <= 2) {
        return n;
      }
      //状态1
      int p1 = 1;
      //状态2
      int p2 = 2;

      for (int i = 2; i < n ;i ++) {
        //计算当前状态
        int cur = p1 + p2;
        p1 = p2;
        p2 = cur;
      }
      return p2;
    }
}
