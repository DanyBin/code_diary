import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2021/4/2 下午6:40
 * @Decr TODO
 * @Link TODO
 **/
public class Test {

    /**
     * 1.输入最小的k个数
     * 2.数组是无序
     * @param nums
     * @param k
     */
    public static int[] min(int[] nums,int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] tmp = new int[k];
        for (int i=1; i< nums.length; i ++) {
            int t = nums[i-1];
            int v = nums[i];
            if (t > v) {
                nums[i] = t;
                nums[i-1] = v;
            }
        }
        for (int i = 0; i < k;i++) {
            tmp[i] = nums[i];
        }
        return tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,8,4,5,6,7};
        System.out.println(Arrays.toString(min(nums, 4)));
    }
}
