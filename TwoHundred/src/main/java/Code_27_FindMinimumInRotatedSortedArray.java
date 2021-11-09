/**
 *
 * Input: [3,4,5,1,2],
 * Output: 1
 *
 */
public class Code_27_FindMinimumInRotatedSortedArray {
  public int findMin(int[] nums) {
    int l = 0;
    int h = nums.length - 1;
    while (l < h) {
      int mid = l + (h-l)/2;
      //有顺序 从小到大时
      if (nums[mid] <= nums[h]) {
        h = mid;
      } else {
        l = mid + 1;
      }
    }
    return nums[l];
  }
}
