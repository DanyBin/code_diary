import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 */
public class Code_44_Permutations2 {

  public static void main(String[] args) {
    final Code_44_Permutations2 permutations2 = new Code_44_Permutations2();
    final List<List<Integer>> lists = permutations2.permuteUnique(new int[]{1, 1, 2});
    System.out.println(lists);

  }
  public List<List<Integer>> permuteUnique(int[] nums) {
    if (null == nums || nums.length == 0) {
      return Collections.emptyList();
    }
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> middle = new ArrayList<>();
    boolean[] visit = new boolean[nums.length]; //用于标记已访问
    backtracking(nums,ret,middle,visit);
    return ret;
  }

  public void backtracking(int[] nums,List<List<Integer>> ret,List<Integer> middle,boolean[] visit) {
    //终止条件
    if (middle.size() == nums.length) {
      ret.add(new ArrayList<>(middle));
      return;
    }

    for (int i=0 ;i < nums.length ; i++) {
      //过滤条件，防止重复
      if (i !=0 && nums[i] == nums[i-1] && !visit[i-1]) {
        continue;
      }

      if (visit[i]) {
        continue;
      }

      visit[i] = true;
      middle.add(nums[i]);
      backtracking(nums,ret,middle,visit);
      visit[i] = false;
      middle.remove(middle.size() - 1);
    }
  }
}
