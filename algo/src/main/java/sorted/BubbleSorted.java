package sorted;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName BubbleSorted
 * @Author bin
 * @Date 2019/9/19 下午7:47
 * @Decr TODO
 *      冒泡排序
 *          思路-相邻位置的数据对比，如果满足条件，交换。否则不变.
 *
 *          伪代码
 *              for(i to index){
 *                  for(j to index){
 *                      if(i > j )
 *                          swap (i,j)
 *                  }
 *              }
 *        是原地排序，是稳定排序
 * @Link TODO
 **/
public class BubbleSorted {

    @Test
    public void test(){
        int[] array = {7,6,5,4,3,1,2};
        bulleSorted(array);
    }

    public static void bulleSorted(int[] array){
        for(int i=0; i< array.length; i++){
            for(int j=i+1; j < array.length;j++){
                if(array[i] > array[j]){   //swap（i,j）
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i]=tmp;
                }
            }
        }
        System.out.println("排序后-"+ ArrayUntil.printArray(array));
    }
}
