package containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @ClassName FailFast
 * @Author bin
 * @Date 2020/7/23 下午5:20
 * @Decr TODO
 *      快速报错（fail fast）。
 *          创建一个迭代器，向迭代器所指向的Collection添加元素
 * @Link TODO
 **/
public class FailFast {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();
        Iterator<String> it = c.iterator();
        c.add("object");
        System.out.println(it.next());

    }
}
