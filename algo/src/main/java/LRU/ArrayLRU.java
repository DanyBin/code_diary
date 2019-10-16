package LRU;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArrayLRU
 * @Author bin
 * @Date 2019/10/16 下午7:19
 * @Decr TODO
 *      基于数组实现LRU缓存- 最少使用算法
 *          思路- 对于新增数据，判断数组是否存在
 *                   1.1 存在时，将该值放在数组头部，并移动 K 之前的值
 *                   1.2 不存在时，直接将数据放在头部 ，移动所有数据(不需要判断数据组是否已满，直接覆盖即可)
 *
 *                记录数组的index = k 。当不存在且数据没有满时， k++;
 * @Link TODO
 **/
public class ArrayLRU {
    String[] array; //用于存储数据
    int k; //记录数组大小
    private Map<String, Integer> holder;  //用于存放数据，并判断

    public ArrayLRU(){
        array = new String[5];
        k = 0;
        holder = new HashMap<String, Integer>();
    }

    @Test
    public void test(){
        ArrayLRU arrayLRU = new ArrayLRU();
        arrayLRU.offer("a");
        arrayLRU.offer("b");
        arrayLRU.offer("c");
        arrayLRU.offer("d");
        arrayLRU.offer("e");
        arrayLRU.offer("a");

        for(String value : arrayLRU.array){
            System.out.println(value);
        }
    }
    /**
     * 模拟访问某个值
     * @param value
     */
    public void offer(String value){
        if (value == null) {
            throw new IllegalArgumentException("该缓存容器不支持null!");
        }
        if(!holder.containsKey(value)){
            if(ifFull()){
                removeAndCache(value);
            }else {
                cache(value,k);
            }
        }else {
            Integer index = holder.get(value);
            update(value,index);
        }
    }

    /**
     * 缓存满的情况，踢出后，再缓存到数组头部
     * @param value
     */
    public void removeAndCache(String value){
        String key = array[--k];
        holder.remove(key);
        cache(value,k);
    }

    /**
     * 缓存数据
     * @param value
     * @param count
     */
    public void cache(String value,int count){
        rightShift(count);
        array[0] = value;
        holder.put(value,0);
        k++;
    }

    public void update(String value,int index){
        rightShift(index);
        array[0] = value;
        holder.put(value,0);
    }

    /**
     * end左边的数据统一右移一位
     * @param end
     */
    private void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            array[i + 1] = array[i];
            holder.put(array[i], i + 1);
        }
    }

    public boolean ifFull(){
        return k == array.length ? true:false;
    }
}
