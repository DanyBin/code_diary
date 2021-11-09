import java.util.Arrays;
import java.util.List;

/**
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 * 题目描述：把归并结果存到第一个数组上。
 */
public class Code_05_MergeSortedArray {
  public static void main(String[] args) {
    int[] num1 = {1,2,3,0,0,0};
    int[] num2 = {2,5,6};


    final int[] ints = mergerSort(num1, num2,3,3);
    System.out.println();
    Arrays.stream(ints).forEach(i -> System.out.println(i));
  }

  private static int[] mergerSort(int[] var1,int[] var2,int n,int m) {
    int index1 = n - 1;
    int index2 = m - 1;
    int indexMerget = m + n - 1;
    while (index2 >= 0) {
      if (index1 < 0) {
        var1[indexMerget--] = var2[index2--];
      } else if (index2 < 0) {
        var1[indexMerget--] = var1[index1--];
      } else if (var1[index1] > var2[index2]) {
        var1[indexMerget--] = var1[index1--];
      } else {
        var1[indexMerget--] = var2[index2--];
      }
    }
    return var1;
  }
}
