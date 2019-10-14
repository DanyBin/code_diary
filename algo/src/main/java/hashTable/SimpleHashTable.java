package hashTable;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName SimpleHashTable
 * @Author bin
 * @Date 2019/10/14 下午7:33
 * @Decr TODO
 *      散列表(Hash table, 也称为哈希表)
 *      定义:  散列表是「数组支持按照下标随机访问」，时间复杂度为O(1)特性。
 *             通过「散列函数」把元素的键值映射为「下标」，然后将「数据存储在数组」中对应「下标」的位置。
 *
 *       查询时: 按照键值时，使用同样的「散列函数」，将键值转换数组下标，从对应数组的下标的位置获取数据
 *
 *       散列函数-可以定义成 hash(key) ,其中key表示元素的键值，hash(key) 的值表示 经过散列函数计算得到的散列值。
 *
 *       散列表有存在「散列冲突」问题
 *          哈希算法被计算的数据是无限的，而计算后的结果范围有限，因此总会存在不同的数据经过计算后得到的值相同，造成冲突
 *
 * @Link TODO
 **/
public class SimpleHashTable {
    int[] array;

    @Test
    public void test() throws Exception{
        /**
         * 简单的hash Table。
         * 1. 通过获取 个位数，作为 下标。将数据存储在数组中
         */
        array = new int[10];
        int[] arrayValue = {1117,229,33,44,25};
        for(int i=0; i < arrayValue.length;i++){
            insert(arrayValue[i]);
        }

        System.out.println(ArrayUntil.printArray(this.array));
    }
    public int hashFun(int key){
        int hashKey =  key%10;
        return hashKey;
    }

    public void insert(int value) throws  Exception{
        int key = hashFun(value);

        //不解决冲突，当冲突时，抛出异常
        if(array[key]> 0){
            throw  new Exception("该Key已经存在");
        }
        array[key] = value;
    }

    public int find (int value){
        int key = hashFun(value);
        return array[key];
    }
}
