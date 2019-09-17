package sorted;

import org.junit.Test;

import static until.ArrayUntil.printArray;

/**
 * @ClassName MergeSort
 * @Author bin
 * @Date 2019/9/17 下午7:22
 * @Decr TODO
 * 归并排序
 * <p>
 * 思路 - 将数组分割成前后两个部分，再将两个数组进行排序，排序后的两个数据进行合并。
 * 即将一个大排序，分割成小排序，再将小排序进行合并。
 * 采用的是分治思想，利用递归方式来实现分割，创建一个合并函数
 * 公式 - mergersort(T) = mergersort(mergersort(T1)) + mergersort(mergersort(T2))
 * <p>
 * 伪代码 -
 * split(int[] array,int low, int high){   //递归方式- 分割数组
 * if(low < high){
 * p = (low+high)/2  //均匀分割
 * <p>
 * split(array,low,p)
 * split(array,p+1,high)
 * <p>
 * mergerSort(array,low,p,high) //当递归截止是，将两个数组进行排序&合并
 * }
 * }
 * <p>
 * <p>
 * mergerSort(int[] array,int low,int p , int high){ //两个数组-排序&合并
 * show code
 * }
 * @Link TODO
 **/
public class MergeSort {

    @Test
    public void Test(){
        int[] array = {7,6,5,4,3,1,2};
        splitArray(array,0,array.length-1);
        System.out.println("最终数组:"+printArray(array));
    }

    /**
     * @param array 数组
     * @param low   最小索引
     * @param high  最大索引
     */
    public static void splitArray(int[] array, int low, int high) {

        if (low < high) { //截止递归条件

            int p = (low + high) / 2;   //获取中间位置

            //递归分割为两个数组
            splitArray(array, low, p);
            splitArray(array, p+1, high);


            //两个数组-合并&排序
            System.out.println("排序&合并的两个数组下标:" + low + "    " + p + "   " + high);
            mergerSort2Array(array, low, p, high);
        }
    }

    /**
     * @param array 数组
     * @param low   最小索引
     * @param p     分割数组-标志索引
     * @param high  最大索引
     */
    public static void mergerSort2Array(int[] array, int low, int p, int high) {
        int[] tmpArray = new int[high - low + 1]; //创建临时数组
        int tmpSize = 0;
        int i = low;
        int j = p+1;

        //两个数组排序-从小到大
        while (i <= p && j <= high) {
            if (array[i] < array[j]) {
                tmpArray[tmpSize++] = array[i++];
            } else {
                tmpArray[tmpSize++] = array[j++];
            }
        }

        //将剩余数组copy -> tmpArray
        int beginIndex = j;
        int size = high;
        if(beginIndex > size){
            beginIndex = i;
            size = p;
        }
        while(beginIndex <= size){
            tmpArray[tmpSize++]= array[beginIndex++];
        }

        System.out.println("原始数组" + printArray(array));
        //将tmpArray排序的数组-替换array
        for (int k = 0; k <tmpArray.length; k++) {
            array[low++] = tmpArray[k];
        }
        System.out.println("排序数组" + printArray(array));
    }

}
