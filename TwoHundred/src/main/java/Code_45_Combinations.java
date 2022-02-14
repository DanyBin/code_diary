import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 排列组合 -- 使用回溯的思想
 */
public class Code_45_Combinations {

  private int k;
  private int n;

  public static void main(String[] args) {
    final Code_45_Combinations combinations = new Code_45_Combinations();
    final List<List<Integer>> combine = combinations.combine(4, 2);
    System.out.println(combine);
  }
  public List<List<Integer>> combine(int n,int k) {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> middle = new ArrayList<>();
    boolean[] visit = new boolean[n+1]; //用于标记
    this.k = k;
    this.n = n;
    backTracking(ret,middle,visit,1);
    return ret;
  }

  public void backTracking(List<List<Integer>> ret,List<Integer> middle,boolean[] visit,int num) {
    if (middle.size() == k) {
      ret.add(new ArrayList<>(middle));
      return;
    }

    for (int i=num ;i <= n; i ++) {
      //忽略已经走过的
      if (visit[i]) {
        continue;
      }
      visit[i] = true;
      middle.add(i);
      //注意，防止重复。元素位置只向前
      num++;
      backTracking(ret,middle,visit,num);
      visit[i] = false;
      middle.remove(middle.size() - 1);
    }
  }
}
