package containers.map;

import java.util.LinkedHashMap;

/**
 * @ClassName LinkedHashMapDemo
 * @Author bin
 * @Date 2020/7/22 下午4:35
 * @Decr TODO
 * @Link TODO
 **/
public class LinkedHashMapDemo {
    public static void main(String[] args) {

        //LRU算法
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(10,0.75f,true);
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"d");
        map.put(5,"e");
        map.put(6,"f");
        map.put(7,"j");
        map.put(8,"k");
        map.put(9,"l");
        map.put(10,"s");
        System.out.println(map);

        for(int i=0;i < 6;i ++){
            map.get(i);
        }
        System.out.println(map);
    }
}
