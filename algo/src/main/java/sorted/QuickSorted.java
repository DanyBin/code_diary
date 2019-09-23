package sorted;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName QuickSorted
 * @Author bin
 * @Date 2019/9/16 下午8:18
 * @Decr TODO
 *  快速排序
 *      思路-分治&递归， 采用一个中间值p 作为分割点。那么只需要将 low -> p 与 p+1 -> high 之间的数据进行排序即可。
 *          直到区间为1时，数据已经排序好。
 *          先分区排序 - 在进行递归。从上而下的解决问题。
 *
 *      递归公式: quickSort(low...high) = quickSort(low...p)+quickSort(p+1...high)
 *      终止条件low >= high
 *
 * @Link TODO
 **/
public class QuickSorted {

    /**
     * 递归遍历数组，分区
     *
     * @param array 数组
     * @param low   最小索引
     * @param high  最大索引
     */
    public static void splitArray(int[] array, int low, int high) {
        //递归终止条件
        if (low >= high) {
            return;
        }
        System.out.println("分割索引" + low + ' ' + high);
        int partition = partition(array, low, high);    //下一个分区点
        splitArray(array, low, partition - 1);
        splitArray(array, partition + 1, high);

    }

    /**
     * 分区数组&排序
     * 采用选择排序的方式来进行更新。将数组分割为已「排序空间」 和「未排序空间」 分割索引为 i
     *
     * @param array 数组
     * @param low   最小索引
     * @param high  最大索引
     */
    public static int partition(int[] array, int low, int high) {
        int pivot = array[high]; //默认分割点
        System.out.println("未排序前-" + ArrayUntil.printArray(array));
        int i = low;
        for (int j = low; j < high; j++) { //遍历数组-[未排序空间]
            //采用选择排序。添加到「排序空间」
            if (array[j] < pivot) {
                if (i == j) { //不需交换。(此时是排序好)
                    i++;
                } else {     //交换 - 当大于pivot时交换元素。
                    int value = array[j];
                    array[j] = array[i];
                    array[i++] = value;
                }
            }
        }

        //交换数据，将分割点排序
        int value = array[high];
        array[high] = array[i];
        array[i] = value;
        System.out.println("排序后-" + ArrayUntil.printArray(array));

        return i;
    }

    @Test
    public void test() {
        int[] array = {5, 4, 3, 2, 7, 4, 6};
        splitArray(array, 0, array.length - 1);
    }


}
