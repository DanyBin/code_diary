import java.util.ArrayList;
import java.util.List;

/**
 *
 *给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code_50_SubsetsII {
  public static void main(String[] args) {
    int[] nums = new int[] {1,2,2};
    final Code_50_SubsetsII ii = new Code_50_SubsetsII();
    final List<List<Integer>> lists = ii.subsetsWithDup(nums);
    System.out.println(lists);
  }

  private int[] nums;
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    boolean[] visit = new boolean[nums.length];
    this.nums = nums;
    for (int i = 0; i <= nums.length; i ++) {
      //Todo 每次dfs时，都是start = 0 的时候
      backTracking(ret,temp,0,i,visit);
    }
    return ret;
  }

  private void backTracking(List<List<Integer>> ret,List<Integer> temp, int start,int target,boolean[] visit) {
    if (temp.size() == target) {
      ret.add(new ArrayList<>(temp));
      return;
    }

    //注意是从start开始的
    for (int i = start; i < nums.length; i ++) {
      //防止重复元素
      if (i != 0 && nums[i] == nums[i - 1] && !visit[i - 1]) {
        continue;
      }

      temp.add(nums[i]);
      visit[i] = true;
      backTracking(ret,temp,i + 1 ,target,visit);
      temp.remove(temp.size() - 1);
      visit[i] = false;
    }
  }
}
