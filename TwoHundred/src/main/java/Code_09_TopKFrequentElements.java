import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 出现频率最多的 k 个元素
 */
public class Code_09_TopKFrequentElements {
  public static int[] topKFrequent(int[] nums , int k) {
    Map<Integer,Integer> map = Maps.newHashMap();
    for (int num : nums) {
      Integer orDefault = map.getOrDefault(num, 1);
      orDefault ++;
      map.put(num,orDefault);
    }
    int[] temp = new int[nums.length];
    for (Integer key : map.keySet()) {
      final Integer integer = map.get(key);
      temp[integer] = key;
    }
    Arrays.sort(temp);
    int[] ret = new int[k];
    for (int i=0 ;i < k ;i++) {
      ret[i] = temp[i];
    }
    return ret;
  }
}
