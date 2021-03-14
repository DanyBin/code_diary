package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GenericVarargs
 * @Author bin
 * @Date 2020/7/8 下午5:41
 * @Decr TODO
 * @Link TODO
 **/
public class GenericVarargs {
    //创建泛型 与 可变参数
    public static <T> List<T> makeList(T...args){
        List<T> result = new ArrayList<T>();
        for(T item:args){
            result.add(item);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(makeList("a"));
        System.out.println(makeList(1,2,3));
    }
}
