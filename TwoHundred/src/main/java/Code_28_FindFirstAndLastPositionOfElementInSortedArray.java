/**
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 *
 * 题目描述：给定一个有序数组 nums 和一个目标 target，要求找到 target 在 nums 中的第一个位置和最后一个位置。
 *
 * 可以用二分查找找出第一个位置和最后一个位置，但是寻找的方法有所不同，
 * 需要实现两个二分查找。我们将寻找 target 最后一个位置，转换成寻找 target+1 第一个位置，再往前移动一个位置。
 * 这样我们只需要实现一个二分查找代码即可。
 */
public class Code_28_FindFirstAndLastPositionOfElementInSortedArray {
  public int[] searchRange(int[] nums,int target) {
    final int first = findFirst(nums, target);
    //寻找大于target的元素的第一个位置
    final int last = findFirst(nums, target + 1) - 1;
    if (first == nums.length || nums[first] != target) {
      return new int[]{-1,-1};
    } else {
      return new int[]{first,Math.max(first,last)};
    }
  }
  public int findFirst(int[] nums,int target) {
    int l = 0;
    int h = nums.length - 1;
    while (l < h) {
      int mid = l  + (h -l)/2;
      if (nums[mid] >= target) {
        h = mid;
      } else {
        l = mid + 1;
      }
    }
    return l;
  }
}
