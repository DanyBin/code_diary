package containers;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName CollectionDataTest
 * @Author bin
 * @Date 2020/7/21 下午12:06
 * @Decr TODO
 * @Link TODO
 **/
public class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(new Government(),4));
        set.addAll(CollectionData.list(new Government(),4));
        System.out.println(set);
    }
}
