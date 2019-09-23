package stack;

import org.junit.Test;
import until.ArrayUntil;

/**
 * @ClassName StarkArray
 * @Author bin
 * @Date 2019/9/23 下午8:06
 * @Decr TODO
 * 栈 -    栈时仅对于栈顶做插入与删除操作。是一个单向的。
 * 空间复杂度为O(n)，应该存储数据不确定长度。
 * 插入与删除的复杂都为O(1),只对一个元素进行操作。
 * 1. 基于数组的实现
 * 1.new [],将元素插入到数组中，并记录元素的下标索引
 * 2.出栈下标索引 -- 。 入栈，下标索引++  O(1)
 * 3.数组慢时，扩容为原来的2倍。遍历数据进行cpoy ，时间复杂度 O(n)
 * 2. 基于链表的实现
 * 1. 初始化一个Node
 * 2. 入栈时，采用链尾的方式插入，将新的Node作为头节点
 * 3. 出栈时，将头节点直接返回，并更新Node。将头节点.next 作为头节点
 * @Link TODO
 **/
public class StarkArray {
    int[] array;
    int size;

    public StarkArray() {
        array = new int[5];
        size = 0;
    }

    /**
     * 入栈操作-
     *
     * @param value
     */
    public void push(int value) {
        size++;
        if (size == array.length) {
            //扩容数组
            arrayCopy();
        }
        for (int i = size - 1; i >= 0; i--) {    //移动数据
            array[i + 1] = array[i];
        }
        array[0] = value;
    }


    /**
     * 出栈操作
     *
     * @return
     */
    public int pop() {
        if (size == 0) {
            System.out.println("栈已经空");
            return -1;
        }
        int value = array[0];
        for (int i = 0; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return value;
    }

    /**
     * 扩容数据操作
     */
    public void arrayCopy() {
        int[] tmp = new int[size * 2];
        for (int i = 0; i < array.length; i++) {
            tmp[i] = array[i];
        }
        array = tmp;
    }

    @Test
    public void test() {
        StarkArray starkArray = new StarkArray();
        for (int i = 1; i < 10; i++) {
            starkArray.push(i);
        }
        System.out.println(ArrayUntil.printArray(starkArray.array));

        for (int i = 1; i < 12; i++) {
            System.out.println(starkArray.pop());
        }
    }
}
