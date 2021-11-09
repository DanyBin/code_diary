import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，
 * 而 3 和 11 不是。
 *
 *
 * ```java
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * ```
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_32_PerfectSquares {

  public int numSquares(int n) {
    final List<Integer> squares = generateSquares(n);
    Queue<Integer> queue = new LinkedList<>(); //用一个队列来存储，每次遍历得到的节点
    boolean[] market = new boolean[n+1]; //用一个数据组标记，已经走过的节点
    queue.add(n);
    market[n] = true;
    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      level++;
      //遍历
      while (size-- > 0) {
        //当前
        final Integer cur = queue.poll();
        for (int s : squares) {
          int next = cur - s;
          if (next < 0) {
            break;
          }

         // 两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边
          if (next == 0) {
            return level;
          }

          if (market[next]) {
            continue;
          }

          //记录走过的节点
          market[next] = true;
          queue.add(next);
        }
      }
    }
    return n;
  }

  //生成1，4、9 、 n的平台数序列
  private List<Integer> generateSquares(int n){
    List<Integer> ret = new ArrayList<>();
    int sqart = 1;
    int diff = 3;
    while (sqart <= n) {
      ret.add(sqart);
      sqart += diff;
      diff += 2;
    }
    return ret;
  }
}
