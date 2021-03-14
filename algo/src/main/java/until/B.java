package until;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName B
 * @Author bin
 * @Date 2021/1/14 下午6:44
 * @Decr TODO
 * @Link TODO
 **/
public class B{
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(4);
        list.add(5);

        list.add(0,10);
        System.out.println(list.toString());
    }
}
