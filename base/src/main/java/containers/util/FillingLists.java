package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName FillingLists
 * @Author bin
 * @Date 2020/7/21 上午11:50
 * @Decr TODO
 * @Link TODO
 **/
public class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list =  new ArrayList<StringAddress>(
                Collections.nCopies(4,new StringAddress("hello"))
        );

        System.out.println(list);
        Collections.fill(list,new StringAddress("world"));
        System.out.println(list);
    }
}
