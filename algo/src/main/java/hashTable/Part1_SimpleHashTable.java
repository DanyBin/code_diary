package hashTable;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName Part1_SimpleHashTable
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
 *       散列表有存在「散列冲突」问题如下:
 *          哈希算法被计算的数据是无限的，而计算后的「结果范围有限」，因此总会存在不同的数据经过计算后得到的值相同，造成冲突.
 *
 *       解决「散列冲突」的方法,常用有两种 开发寻址法（open adressing） 和链表法（chaining）
 *
 *          1. 开发寻址法
 *             思路: 当出现「散列冲突」时，重新探测一个空闲位置，将其插入。
 *
 *             线性探测法(Linear Probing): 当key 经过「散列函数」的值，出现冲突时，则从当前位置依次向后查找，是否有空闲位置，并插入。
 *             二次探测(Quadratic probing): 与线性探测法一样，当探测的下标是「二次方」也就是 hash(key)+0,hash(key)+1^2 + hash(key)+2^2
 *             双重散列(Double hashing): 使用一组散列函数。如果出现冲突，则更换散列函数进行尝试，最终插入数据
 *
 *             装载因子(load factor): 表示散列表中空位的多少
 *             计算公式: 散列表的装载因子 = 填入表中元素个数/ 散列表的长度
 *             装载因子越大，说明空闲位置越少，冲突越多，散列表的性能会下降
 *
 *         2. 链表法
 *             思路: 在散列中，每个「桶(bucket)」/「槽(slot)」会对应一个链表，所有散列值相同的元素，都会放到相同槽位对应的链表中。
 *             数据插入: 通过散列函数计算对应的槽位，将其插入对应的链表中
 *             数据查询/删除: 通过散列函数计算对应的槽位，遍历对应的链表查询或者删除
 *
 * @Link TODO
 **/
public class Part1_SimpleHashTable {
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
