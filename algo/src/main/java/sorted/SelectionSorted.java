package sorted;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName SelectionSorted
 * @Author bin
 * @Date 2019/9/23 下午2:19
 * @Decr TODO
 *      选择排序
 *          思路- 将数组分为「未排序空间」与「排序空间」，遍历「未排序空间」获取最小值，加入「排序空间」
 * @Link TODO
 **/
public class SelectionSorted {

    @Test
    public void test(){
        int[] array = {5, 4, 3, 2, 7, 4, 6};
        selectionSort(array);
        System.out.println(ArrayUntil.printArray(array));

    }
    public static void  selectionSort(int[] arr){
        for(int i=0;i<arr.length;i++){
             int value = arr[i] ; //最小值
             int k=i; //最小值索引
             int j = i + 1;

             for(;j < arr.length; j++){
                 if(arr[j] < value){
                     value = arr[j];
                     k = j;
                 }
             }

            // swap
            arr[k] = arr[i];
            arr[i] = value;
        }
    }
}
