import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 *
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 *
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 *
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_46_CombinationSum {

  public List<List<Integer>> combinationSum(int[] candidates,int target) {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> middle = new ArrayList<>();
    brackTracking(ret,middle,candidates,0,target);
    return ret;
  }

  /**
   * 终止条件为 筛选的元素集合 > target
   * or  筛选的元素集合 == target
   */
  public void brackTracking(List<List<Integer>> ret,List<Integer> middle,int[] candidates,int start,int target) {
    if (target == 0) {
      ret.add(new ArrayList<>(middle));
      return;
    }

    for (int i = start ; i < candidates.length; i ++) {
      //过滤条件
      if (candidates[i] <= target) {
        middle.add(candidates[i]);
        //注意，使用 target - candidates[i] 来计算
        brackTracking(ret,middle,candidates,i,target - candidates[i]);
        middle.remove(middle.size() - 1);
      }
    }
  }
}
