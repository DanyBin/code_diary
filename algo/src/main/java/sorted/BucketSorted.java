package sorted;

import org.junit.Test;
import until.ArrayUntil;

import java.util.ArrayList;

/**
 * @ClassName BucketSorted
 * @Author bin
 * @Date 2019/9/24 下午7:07
 * @Decr TODO
 *      桶排序
 *          思路-
 *              1. 将要排序的数据分到几个有序的桶里，
 *              2. 每个桶的数据单独进行快速排序
 *              3. 将每个桶的数据取出，组成有序
 *
 * @Link TODO
 **/
public class BucketSorted {

    @Test
    public void test(){
        int[] array = {7,6,5,4,3,1,2};
        bucketSort(array,3);
        System.out.println(ArrayUntil.printArray(array));
    }

    /**
     * 桶排序
     * @param array 数组
     * @param bucketSize   桶容量
     */
    public void bucketSort(int[] array , int bucketSize){

        //数组最小值
        int minValue = array[0];
        //数组最大值
        int maxValue = array[1];

        for(int i=0; i < array.length; i ++){
            if(array[i] < minValue){
                minValue = array[i];
            }
            if(array[i] > maxValue){
                maxValue = array[i];
            }
        }

        //桶数量
        int bucketCount = (maxValue-minValue)/bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount]; //桶计数- 保存每个桶的数据写入情况。即每个桶中元素的个数

        //将数组中的值，分配到桶中
        for(int i=0; i < array.length ; i++){
            int bucketIndex = (array[i] - minValue) / bucketSize; //计算数据属于某个桶

            if(indexArr[bucketIndex] == buckets[bucketIndex].length ){  //当某个桶数据已满
                //扩容桶
                ensureCapacity(buckets,bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = array[i]; //桶新增数据
        }


        int k = 0 ;
        //对每个桶内的数据进行排序- 快速排序
        for(int i=0;i < bucketCount;i++){

            if(indexArr[i] == 0 ){ //当桶内没有数据，忽略
                continue;
            }

            quickSort(buckets[i],0,indexArr[i]-1);

            for(int j =0; j < indexArr[i]; j++){    //遍历某个桶
                array[k++] = buckets[i][j];  //回填排序好的数据
            }

        }
    }

    /**
     * 扩容桶-数组扩容
     * @param buckets
     * @param bucketIndex
     */
    public void ensureCapacity(int[][] buckets,int bucketIndex){
        int[] bucket = buckets[bucketIndex];
        int[] tmp = new int[bucket.length * 2];
        for(int i=0; i < bucket.length; i++){
            tmp[i] = bucket[i];
        }
        buckets[bucketIndex] = tmp;
    }

    /**
     * 快速排序
     * @param array
     */
    public  void quickSort(int[] array,int left ,int right){
        if(left > right){
            return;
        }
        int partiton  = partitonSort(array, left, right);
        quickSort(array,left,partiton-1);
        quickSort(array,partiton+1,right);

    }

    /**
     * 指定切分点-
     * @param array
     * @param left
     * @param right
     * @return
     */
    public  int  partitonSort(int[] array,int left,int right){
        int tmp = array[left]; //获取切换点 pviot

        while (left < right){
            //先判断基准数和后面的数依次比较
            while (tmp < array[right] && left < right){
                --right;
            }

            // 当基准数大于了 arr[right]，则填坑
            if(left < right){
                array[left] = array[right];
                ++left;
            }

            while (tmp > array[left] && left < right){
                ++left;
            }

            if(left < right){
                array[right] = array[left];
                --right;
            }
        }

        array[left] = tmp;
        return left;
    }
}
