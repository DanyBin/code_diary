package containers.set;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @ClassName SortedSetDemo
 * @Author bin
 * @Date 2020/7/22 下午3:11
 * @Decr TODO
 * @Link TODO
 **/
public class SortedSetDemo {
    public static void main(String[] args) {
        //字典排序
        SortedSet<String> sortedSet =  new TreeSet<String>();
        Collections.addAll(sortedSet,"basd a c d e f".split(","));
        System.out.println(sortedSet);

    }
}
