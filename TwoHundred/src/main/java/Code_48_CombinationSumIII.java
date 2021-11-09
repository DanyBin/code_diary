import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为n 的k个数的组合。组合中只允许含有 1 -9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 *
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Code_48_CombinationSumIII {

  public static void main(String[] args) {
    final Code_48_CombinationSumIII sumIII = new Code_48_CombinationSumIII();
    final List<List<Integer>> lists = sumIII.combinationSum3(3, 6);
    System.out.println(lists);
  }
  private int[] nums = {1,2,3,4,5,6,7,8,9};

  public List<List<Integer>> combinationSum3(int k,int n) {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> middle = new ArrayList<>();
    backTracking(ret,middle,k,0,n);
    return ret;
  }
  public void backTracking(List<List<Integer>> ret,List<Integer> middle,int k,int start,int n) {
    if (n == 0 && middle.size() == k) {
      ret.add(new ArrayList<>(middle));
      return;
    }
    for (int i = start; i < nums.length;i ++) {
      if (nums[i] <= n) {
        middle.add(nums[i]);
        backTracking(ret,middle,k,i + 1,n - nums[i]);
        middle.remove(middle.size() - 1);
      }

    }
  }
}
