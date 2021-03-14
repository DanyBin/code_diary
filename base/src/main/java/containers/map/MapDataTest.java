package containers.map;

import array.util.CountingGenerator;

/**
 * @ClassName MapDataTest
 * @Author bin
 * @Date 2020/7/21 下午12:26
 * @Decr TODO
 * @Link TODO
 **/
public class MapDataTest {
    public static void main(String[] args) {
        System.out.println(MapData.map(new Letters(),11));
        System.out.println(MapData.map(new CountingGenerator.Character(),"aaa",5));
        System.out.println(MapData.map(new Letters(),"a"));
    }
}
