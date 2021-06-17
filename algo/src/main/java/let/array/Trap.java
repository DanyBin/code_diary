package let.array;

import org.junit.Assert;

/**
 * @ClassName Trap
 * @Author bin
 * @Date 2021/4/3 下午1:37
 * @Decr TODO
 *  接雨水的题
 *      思路
 *       计算[left，righ]
 *       不能是0.中间是0，则跳过
 *       int min = Math.min(height[left],height[right]);  获取最小值。 快慢指针的方式
 *       int index = right - left;
 *       面积 = min * index
 *       累加
 *       终止条件。 right == height.length -1;
 * @Link TODO
 **/
public class Trap {
    /**
     * 思路
     *   1. 获取左边的最大值 - 数组
     *   2. 获取右边的最大值 - 数组
     *   其实是通过 min + height[min] =  max 的这种方式
     *   若是 min - height[index](这是是0的时候)， 就是接雨的雨量了
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] leftMax = new int[n];
        //初始化第一个节点
        leftMax[0] = height[0];

        for (int i = 1;i < n; i ++) {
            //比较大小，更新位置
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n-1] = height[n-1];
        for (int i = n -2; i >= 0;i --) {
            rightMax[i] = Math.max(rightMax[i+1],height[i]);
        }

        /**
         * 例如 -  通过min 与height 的最小值。做差求接雨量
         *
         */
        int ant = 0;
        for (int i=0;i < n;i ++) {
            ant += Math.min(leftMax[i],rightMax[i]) - height[i];
        }
        return ant;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,0,1};
        int trap = trap2(height);
        Assert.assertTrue(1==trap);
    }
}
