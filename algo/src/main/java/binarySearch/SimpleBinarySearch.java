package binarySearch;

import org.junit.Test;

/**
 * @ClassName SimpleBinarySearch
 * @Author bin
 * @Date 2019/10/9 下午4:34
 * @Decr TODO
 *      二分查找简单版
 *          思路 - 对一个「有序的数组」，通过获取中间值，与「查找值」进行比较，判断该「元素」是否存在
 *          时间复杂度 - O(logN)
 *
 *       二分查找的局限
 *          1. 依赖顺序表结构-也就是数组。 能够简写随机访问数据，链表不支持
 *          2. 必须是有序数据，适用一次排序，多次查找
 *          3. 数据太小不适合，之间for循环即可。特例-比较操作耗时的，适用二分查找
 *          4. 数据太大不适合，内存无法支持「连续」的空间
 *
 *
 * @Link TODO
 **/
public class SimpleBinarySearch {


    @Test
    public void test(){
        int[] array = {1,2,3,4,5,6,7,8};
        System.out.println(forEachBinarySearch(array,7));
        System.out.println(recursionBinarySearch(array,0,array.length-1,7));
    }

    /**
     * 采用基础for循环的方式，进行二分查找
     * @param arr
     */
    public int forEachBinarySearch(int[] arr,int value){
            int low = 0 ;
            int high = arr.length -1 ;

            while (low <= high){ //注意是 <= ,比较到low=high
                int mid = (low+high)/2;
                if(arr[mid] == value){
                    return mid;
                }else if(arr[mid] > value){
                    high = mid-1;
                }else {
                    low = mid+1;
                }
            }
            return  -1;
    }

    /**
     * 采用递归，实现二分查找
     *  递归公式 = recursionBinarySearch() = recursionBinarySearch(recursionBinarySearch()) +  recursionBinarySearch(recursionBinarySearch())
     *  终止条件 = low > high
     * @param arr
     * @param low
     * @param high
     */
    public int recursionBinarySearch(int[] arr,int low,int high,int value){
        if(low > high){
            return -1;
        }
        int mid = (low+high)/2;

        if(arr[mid] == value){
            return mid;
        }
        if(arr[mid] < value){
          return   recursionBinarySearch(arr,mid+1,high,value);
        }else {
           return recursionBinarySearch(arr,low,mid-1,value);
        }
    }
}
