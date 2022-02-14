/**
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class Code_10_SortColors {
  public int[] sortColors(int[] nums) {
    int zero = -1;
    int one = 0;
    int two = 2;
    while (one < two) {
      if (nums[one] == 0) {
        swap(nums,++zero,one++);
      } else if (nums[one] == 2) {
        swap(nums,--two,one);
      } else {
        ++ one;
      }
    }
    return nums;
  }
  public void swap(int[] nums,int i,int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
