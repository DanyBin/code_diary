import com.google.common.collect.Lists;

import java.util.List;

/**
 * nput: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 */
public class Code_21_PartitionLabels {

  public List<Integer> partitionLabels(String str) {
   //26个字母
    int[] nums = new int[26];
    //记录位置 -- 每个元素的最大值
    for (int i = 0; i< str.length() ; i++) {
      nums[indexOfChar(str.charAt(i))] = i;
    }
    List<Integer> ret = Lists.newArrayList();
    int firstIndex = 0;
    while (firstIndex < str.length()) {
      int lastIndex = firstIndex;

      //迭代
      for (int i= firstIndex; i< str.length() && i <= lastIndex; i++) {
        //获取元素位置
        final int index = nums[indexOfChar(str.charAt(i))];
        //如果index 与元素的位置做对比。获取最大的元素
        if (index > lastIndex) {
            lastIndex = index;
        }
        ret.add(lastIndex - firstIndex  + 1);
        firstIndex = lastIndex + 1;
      }
    }
    return ret;
  }

  private int indexOfChar(char c) {
    return c - 'a';
  }
}
