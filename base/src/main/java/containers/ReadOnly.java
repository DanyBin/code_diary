package containers;

import java.util.*;

/**
 * @ClassName ReadOnly
 * @Author bin
 * @Date 2020/7/23 下午4:58
 * @Decr TODO
 *      只读的Collection 或Map
 * @Link TODO
 **/
public class ReadOnly {
    static Collection<String> data = Arrays.asList("a b c d e f".split(" "));

    public static void main(String[] args) {
        Collection<String> c = Collections.unmodifiableCollection(new ArrayList<String>(data));
        System.out.println(c);
        //c.add("t"); -- 运行时异常

        List<String> list = Collections.unmodifiableList(new ArrayList<String>(data));

        ListIterator<String> lit = list.listIterator();
        System.out.println(lit.next());


    }
}
