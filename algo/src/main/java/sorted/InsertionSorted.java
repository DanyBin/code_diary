package sorted;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName InsertionSorted
 * @Author bin
 * @Date 2019/9/19 下午7:57
 * @Decr TODO
 *      插入排序
 *          思路-分治思想，将数组分为两个空间-「排序空间」 和「未排序空间」。 遍历「未排序空间」 与 「排序空间」进行比较，插入数据
 *
 *          伪代码
 *           for( i to n){  //遍历 「未排序空间」
 *              value = arr[i]  //数据
 *              j = i-1         //「排序空间下标索引」
 *              for(j >= 0 ;j --){ //遍历「排序空间」
 *                  if(arr[j] < value){ // 比较
 *                      arr[j+1] = arr[j] //swap
 *                  }else{
 *                      break
 *                  }
 *              }
 *              arr[j+1] = value ; //插入
 *           }
 *
 * @Link TODO
 **/
public class InsertionSorted {

    @Test
    public void test(){
        int[] array = {7,6,5,4,3,1,2};
        insertionSort(array);
        System.out.println(ArrayUntil.printArray(array));
    }

    public static void insertionSort(int[] arr){
        for(int i=1 ; i < arr.length ; i++){    //遍历「未排序」
            int value = arr[i];
            int j = i-1; //「排序」的下标索引
            for(;j>=0; j--){    //遍历「排序」
                if(arr[j] > value){ //compare
                    arr[j+1] = arr[j]; //swap
                }else {
                    break;
                }
            }

            arr[j+1] = value; //插入数据
        }
    }
}
