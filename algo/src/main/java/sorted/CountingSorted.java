package sorted;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName CountingSorted
 * @Author bin
 * @Date 2019/9/24 下午8:06
 * @Decr TODO
 *      计数排序
 *          思路-举例
 *              数组A[4] = {3,2,0,1,3}
 *              数组C[3] = {0,1,1,2}
 *                      注意: 下标索引 才对应 数据A中的数据。而数据C中存在是数据A中的元素的个数
 *
 *              接下来:
 *              对数组C[3]中的数据，进行顺序累加操作。
 *              即变为C[3] = {0,1,2,4}
 *
 *              接下来:
 *              创建一个临时数组R[4] 大小与数组A一致
 *
 *              遍历数组A[4]中的数据，通过数组A中的数据，作为数组C的下标索引，获取出数组C中的数据。同时数组C中的数据减一
 *              将数组R[数组C的数据-1] = 数组A中的数据。
 *
 *              最后
 *              将R[] copy到数据A[]中，完成排序
 *
 * @Link TODO
 **/
public class CountingSorted {


    @Test
    public void test(){
        int[] array = {7,6,5,4,3,1,2};
        countingSort(array,array.length);
        System.out.println(ArrayUntil.printArray(array));
    }

    /**
     * 计数排序
     * @param array -数组
     * @param n     -数组大小
     */
    public void countingSort (int array[],int n){
        if(n < 1) return;

        //获取数组A[]最大值
        int maxValue = array[0];
        for(int i=0; i< array.length; i++){
            if(array[i] > maxValue){
                maxValue = array[i];
            }
        }

        //创建数组B
        int[] arrayB = new int[maxValue];
        for(int i=0; i< array.length; i++){
            arrayB[array[i]-1] = arrayB[array[i]-1] +1;
        }

        //对数组B进行顺序累加操作
        for(int i=0; i< arrayB.length-1; i++){
            arrayB[i+1] = arrayB[i] + arrayB[i+1];
        }

        //创建数组R[]
        int[] arrayR = new int[n];
        for(int i=0; i< array.length; i++){

            //数组A的数据作为数组C的下标索引，获取数组C的数据
            int tmp = arrayB[array[i]-1] ;
            arrayR[tmp-1] = array[i];

            arrayB[array[i]-1] = arrayB[array[i]-1] -1;
        }

        for(int i=0; i< arrayR.length;i++){
            array[i] = arrayR[i];
        }
    }
}
