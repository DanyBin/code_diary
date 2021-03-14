package thredSynchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TestMap
 * @Author bin
 * @Date 2020/9/27 上午11:35
 * @Decr TODO
 * @Link TODO
 **/
public class TestMap {
    static ConcurrentHashMap<String,List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("device1");
                list.add("device2");

                List<String> oldList = map.putIfAbsent("topic1",list);
                if(null != oldList) {
                    oldList.addAll(list);
                }
                System.out.println(map);
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("device11");
                list.add("device22");
                List<String> oldList = map.putIfAbsent("topic1",list);
                if(null != oldList) {
                    oldList.addAll(list);
                }
                System.out.println(map);
            }
        });

        Thread threadThree = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("device111");
                list.add("device222");
                List<String> oldList = map.putIfAbsent("topic2",list);
                if(null != oldList) {
                    oldList.addAll(list);
                }
                System.out.println(map);
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}
