import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 *
 * candidates中的每个数字在每个组合中只能使用一次。
 *
 * 注意：解集不能包含重复的组合。
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_47_CombinationSumII {


  public static void main(String[] args) {
    final Code_47_CombinationSumII sumII = new Code_47_CombinationSumII();
    final List<List<Integer>> lists = sumII.combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    System.out.println(lists);
  }

  public List<List<Integer>> combinationSum(int[] candidates,int target) {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> middle = new ArrayList<>();
    boolean[] visit = new boolean[candidates.length];
    backTracking(ret,middle,0,target,candidates,visit);
    return ret;
  }

  public void backTracking(List<List<Integer>> ret,List<Integer> middle,int start,int target,int[] candidates,boolean[] visit) {
    if (target == 0) {
      ret.add(new ArrayList<>(middle));
      return;
    }

    for (int i = start; i < candidates.length ; i ++) {
      //防止重复
      if (i !=0 && candidates[i] == candidates[i-1] && !visit[i-1]) {
        continue;
      }

      if (candidates[i] <= target) {
        visit[i] = true;
        middle.add(candidates[i]);
        backTracking(ret,middle,i + 1,target - candidates[i],candidates,visit);
        visit[i] = false; //标记已走过
        middle.remove(middle.size() - 1);
      }
    }
  }
}
