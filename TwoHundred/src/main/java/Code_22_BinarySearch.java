/**
 *  二分查找也称为折半查找，每次都能将查找区间减半，这种折半特性的算法时间复杂度为 O(logN)。
 */
public class Code_22_BinarySearch {

  /**
   * Input : [1,2,3,4,5]
   * key : 3
   * return the index : 2
   * @param nums
   * @param key
   * @return
   */
  public int binarySearch(int[] nums, int key){
    int l = 0;
    int h = nums.length - 1;
    while (l <= h) {
      int m = l + (h - l)/2;
      if (nums[m] == key) {
        return m;
      } else if (nums[m] < key) {
        l = m + 1;
      } else {
        h = m - 1;
      }
    }
    return -1;
  }
}
