import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *  排列组合 - 使用回溯的思想
 */
public class Code_43_Permutations {
  public static void main(String[] args) {
    final Code_43_Permutations permutations = new Code_43_Permutations();
    int[] t = {1,2,3};
    final List<List<Integer>> permute = permutations.permute(t);
    System.out.println(permute);
  }

  public List<List<Integer>> permute(int[] nums) {
    if (null == nums || nums.length == 0) {
      return Collections.emptyList();
    }
    List<List<Integer>> ret = Lists.newArrayList();
    boolean[] visit = new boolean[nums.length];
    List<Integer> l = new ArrayList<>();
    backtrack(nums,visit,l,ret);
    return ret;
  }
  public void backtrack(int[] nums,boolean[] visit,List<Integer> l,List<List<Integer>> ret) {
    if (l.size() == nums.length) {
      ret.add(new ArrayList<>(l));
      return;
    }

    for (int i = 0; i < visit.length; i++) {
      if (visit[i]) {
        continue;
      }
      visit[i] = true;
      l.add(nums[i]);
      backtrack(nums,visit,l,ret);
      //回溯的方式。删除之前的数据
      l.remove(l.size() - 1);
      visit[i] = false;
    }
  }
}
