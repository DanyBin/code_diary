package let.array;

/**
 * @ClassName FindRepeatNumber
 * @Author bin
 * @Date 2021/4/7 上午10:07
 * @Decr TODO
 *      寻找重复的数
 *      输入：nums = [1,3,4,2,2]
        输出：2
 * @Link TODO
 **/
public class FindRepeatNumber {
    public static int findRepeatNumber(int[] nums) {
        //创建数组
        int[] t = new int[nums.length];
        //遍历
        for (int i = 0; i < nums.length; i++) {
            //记录下标的方式
            if (t[nums[i]] != 0) {
                return nums[i];
            }
            //将数组赋值
            t[nums[i]] = i + 1;

        }
        return 0;
    }
}
