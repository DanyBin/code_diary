import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 */
public class Code_49_Subsets {
  private int[] nums;
  public static void main(String[] args) {
    final Code_49_Subsets subsets = new Code_49_Subsets();
    final List<List<Integer>> subsets1 = subsets.subsets(new int[]{1, 2, 3});
    System.out.println(subsets1);
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> m =  new ArrayList<>();
    this.nums = nums;
    for (int i = 0 ;i <= nums.length; i ++) {
      //Todo  注意一直都是从0开始的。 target是目标的大小
      backTracking(ret,m,0,i);
    }
    return ret;
  }

  public void backTracking(List<List<Integer>> ret,List<Integer> m,int start,int target) {
    if (m.size() == target) {
      ret.add(new ArrayList<>(m));
      return;
    }
    for (int i = start; i < nums.length; i ++) {
      m.add(nums[i]);
      backTracking(ret,m,i + 1,target);
      m.remove(m.size() - 1);
    }
  }
}
