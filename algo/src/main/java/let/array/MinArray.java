package let.array;

/**
 * @ClassName MinArray
 * @Author bin
 * @Date 2021/4/7 上午10:54
 * @Decr TODO
 *      有序数组中的最小值
 *
 *      思路 - 二分法查找
 * @Link TODO
 **/
public class MinArray {
    private int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int min = low + (high - low) / 2;

            if (numbers[min] < numbers[high]) {
                high = min;
            } else if (numbers[min] > numbers[high]) {
                low = min + 1;
            } else { //相同的情况下
                high = high - 1;
            }
        }
        return numbers[low];
    }
}
