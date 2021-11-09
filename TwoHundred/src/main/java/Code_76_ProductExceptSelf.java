import java.util.Arrays;

/**
 * 给你一个长度为n的整数数组nums，其中n > 1，返回输出数组output，其中 output[i]等于nums中除nums[i]之外其余各元素的乘积
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 */
public class Code_76_ProductExceptSelf {
  public static void main(String[] args) {
    final Code_76_ProductExceptSelf self = new Code_76_ProductExceptSelf();
    final int[] ints = self.productExceptSelf2(new int[]{1, 2, 3, 4});
    Arrays.stream(ints).forEach(i -> System.out.println(i));
  }

  /**
   * O(n^2)的时间复杂度
   * @param nums
   * @return
   */
  public int[] productExceptSelf(int[] nums) {
      int n = nums.length;
      int[] output = new int[n];
      for (int i = 0; i < n; i++) {
        //小于I的边界
        int v = 1;
        for (int x = 0; x < i; x++) {
          v *= nums[x];
        }

        //大于I的边界
        for (int x = i + 1; x < n; x++) {
          v *= nums[x];
        }
        output[i] = v;
      }
      return output;
  }

  public int[] productExceptSelf2(int[] nums) {
    int n = nums.length;
    int[] output = new int[n];
    int v1 = 1;
    int v2 = 1;

    for (int i = 0 ;i < n ; i++) {
      output[i] = v1;
      v1 *= nums[i];
    }

    for (int i = n - 1;i > 0; i--) {
      v2 *= nums[i];
      output[i-1] *= v2;
    }
    return output;
  }
}