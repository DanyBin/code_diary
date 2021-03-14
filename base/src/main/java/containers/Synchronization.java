package containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @ClassName Synchronization
 * @Author bin
 * @Date 2020/7/23 下午5:17
 * @Decr TODO
 * @Link TODO
 **/
public class Synchronization {
    public static void main(String[] args) {
        Collection<String> c = Collections.synchronizedCollection(new ArrayList<String>());
    }
}
