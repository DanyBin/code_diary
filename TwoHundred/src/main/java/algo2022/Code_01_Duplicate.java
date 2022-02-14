package algo2022;

import java.util.HashSet;
import java.util.Set;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/3.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E9%87%8D%E5%A4%8D%E7%9A%84%E6%95%B0%E5%AD%97.md
 *
 *
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * Input:
 * {2, 3, 1, 0, 2, 5}
 *
 * Output:
 * 2
 *
 *
 */
public class Code_01_Duplicate {

  public static void main(String[] args) {
    final int duplicate = duplicate(new int[]{2, 3, 1, 0, 2, 5});
    System.out.println(duplicate);
  }
  /**
   * 基于排序，对比大小。移动元素的位置
   * @param nums
   * @return
   */
  public static int duplicate(int[] nums){
    for (int i = 0 ; i < nums.length ; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j-1] == nums[j]) {
          return nums[j];
        }
        //移动i的位置
        int tmp = nums[j-1];
        nums[j] = tmp;
        nums[j-1] = nums[j];
      }
    }
    return -1;
  }
}
