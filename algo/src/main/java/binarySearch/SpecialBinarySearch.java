package binarySearch;

import org.junit.Test;

/**
 * @ClassName SpecialBinarySearch
 * @Author bin
 * @Date 2019/10/9 下午7:06
 * @Decr TODO
 *     一、 变形二分查找的问题 （前提条件- 对于一个有重复元素的有序数组）
 *               1. 查找出第一个值等于给定值的元素
 *               2. 查找出最后一个值等于给定值的元素
 *               3. 查找出第一个大于等于给定值的元素
 *               4. 查找最后一个小于等于给定值的元素
 *
 *      二、 循环有序数组，查找"值等于给定值"
 *
 * @Link TODO
 **/
public class SpecialBinarySearch {


    @Test
    public void test(){
        int[] array = {1,2,3,4,5,5,5,6,7,8};
        System.out.println(firstEQ(array,5));
        System.out.println(lastEQ(array,5));
        System.out.println(firstGT(array,5));
        System.out.println(lastLT(array,5));

        int[] loopArray = {4,5,6,7,1,2,3};
        System.out.println(loopBinarySearch(loopArray,2));
    }

    /**
     * 问题一
     * 查找出第一个值等于给定值的元素。「有序数组」
     * 特殊处理 - 当查出的值与给定定值相同时,需要特殊判断
     * @param arr
     * @return
     */
    public int firstEQ(int arr[],int value){
        int low = 0;
        int high = arr.length -1;
        while (low <= high){
            int mid = low+(high-low)/2;

            if(arr[mid] < value){ //当给定值 大于 中间值时，
                low = mid+1;
            }else if(arr[mid] > value){ //当给定值 小于 中间值时
                high = mid-1;
            }else { //当给定值 == 中间值时
                if(mid == 0 || arr[mid -1 ] != value){  //mid=0时，查询结束。  arr[mid-1] != value 即数组中前一个元素 不等于 给定值
                    return mid;
                }else {
                    high = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     * 问题二
     *  查找出最后一个值等于给定值的元素。
     *  参考问题一的解决方案即可
     * @param arr
     * @param value
     * @return
     */
    public int lastEQ(int[] arr,int value){
        int low = 0;
        int high = arr.length -1;
        while (low <= high) {
            int mid = low +((high-low)>>1);

            if(arr[mid] < value){
                low = mid+1;
            }else if(arr[mid] > value){
                high = mid -1 ;
            }else { //当给定值 == 中间值
                if(mid == arr.length -1 || arr[mid+1] != value){
                    return mid;
                }else {
                    low = mid+1;
                }
            }
        }
        return  -1;
    }

    /**
     * 问题三
     *  查找出第一个大于等于给值的元素。
     *  解决方式- 参考以上
     * @param arr
     * @param value
     * @return
     */
    public int firstGT(int[] arr, int value){
        int low = 0;
        int high = arr.length -1;
        while (low <= high){
            int mid  = low + ((high -low)>>1);
            if(arr[mid] >= value){
                if(mid ==  0 || arr[mid-1] < value){ //按照顺序数组，向前判断
                     return mid;
                }else {
                    high = mid-1;
                }
            }else {
                low = mid+1;
            }
        }
        return  -1;
    }

    /**
     * 问题四
     *  查找出最后一个小于等于给定值的元素
     *  参考问题三
     * @param arr
     * @param value
     * @return
     */
    public  int lastLT(int[] arr,int value){
        int low = 0;
        int high = arr.length -1;
        while (low <= high){
            int mid = low +((high -low) >> 1);
            if(arr[mid] <= value){
                if(mid == arr.length -1 || arr[mid+1]> value){ //按照顺序数组，向后判断
                    return mid;
                }else {
                    low = mid+1;
                }
            }else {
                high = mid-1;
            }
        }
        return  -1;
    }

    /**
     * 对于循环有序数组，查找"值等于给定值"
     * @param arr
     * @param value
     */
    public int loopBinarySearch(int[] arr,int value){
        int low = 0;
        int high = arr.length-1;
        int mid = low + ((high - low) >> 1);
        while (low <= high){

            //对于边界条件的判断
            if(arr[low] == value){
                return low;
            }
            if(arr[high] == value){
                return high;
            }
            if(arr[mid] == value){
                return mid;
            }

            mid = low + ((high - low) >> 1);
            if(arr[low]< arr[mid]){  //当arr[low] < arr[mid] ，表示low-> mid 之间是 有序数组，采用二分法进行查找

                if(arr[low] < value && arr[mid] > value){ //当给定值 在 low -> mid 之间时，缩小查询范围
                    high = mid -1;
                }else {
                    low = mid +1; //否则扩大
                }
            }else {
                if(arr[mid] < value && value < arr[high]){//当给定值，在 mid -> high之间时，缩小查询范围
                    low = mid+1;
                }else {
                    high = mid -1;
                }
            }
        }
        return  -1;
    }
}

