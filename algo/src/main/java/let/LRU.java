package let;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRU
 * @Author bin
 * @Date 2021/4/3 下午12:37
 * @Decr TODO
 *  LRU算法 - 当容量满时，淘汰第一元素 - 最近最少使用淘汰算法
 *  思路 - 最少使用
 *        Map<String,Integer>  == <元素，使用次数>
 *        int[] array = new int[] -- 保存元素的数组。 按照使用次数来排序 从低到高
 *
 *        对于新插入的元素。 获取其使用次数 + 1
 *        更新数组，若int[n] > int[n+1]的次数，则移动位置
 *        当数组满时，对于新插入的元素，淘汰数组第一位
 *
 *   单一原则，只做一件事
 * @Link TODO
 **/
public class LRU {
    private String[] array;
    private Map<String,Integer> map;
    private int length;
    private static int Size = 10;

    public LRU() {
        this(Size);
    }

    public LRU(int size) {
        array = new String[size];
        map = new HashMap<>();
    }

    //添加元素
    public void push(String item) {

        if (map.containsKey(item)) {
            map.put(item,map.get(item) + 1);
            insertSwap();
        } else {
            //第一次插入元素
            map.put(item,1);
            insertTail(item);
        }

        if (length != array.length) {
            length++;
        }
    }

    /**
     * 处理下一次插入的元素
     * @param item
     */
    private void insertTail (String item) {
        //数组满了
        if (length == array.length) {
            //需要清空元素
            map.remove(array[0]);
            array[0] = item;
        } else {
            //移动数组位置
            for (int i = length - 1; i >= 0; i --) {
                array[i+1] = array[i];
            }
            array[0] = item;
        }
    }

    /**
     * 处理已经存在的元素
     */
    private void insertSwap() {
        for (int i = 0; i < length - 1; i++) {
            //比较次数，交换位置
            if (map.get(array[i]) > map.get(array[i+1])) {
                String v1 = array[i];
                String v2 = array[i+1];
                array[i] = v2;
                array[i+1] = v1;
            }
        }
    }

    public static void main(String[] args) {
        LRU lru = new LRU(5);
        lru.push("a");
        lru.push("b");
        lru.push("c");
        lru.push("d");
        lru.push("e");
        lru.push("f");

        lru.push("f");
        lru.push("f");
        lru.push("c");
    }
}
