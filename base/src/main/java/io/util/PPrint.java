package io.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * @ClassName PPrint
 * @Author bin
 * @Date 2020/7/24 上午11:00
 * @Decr TODO
 * @Link TODO
 **/
public class PPrint {
    public static String pformat(Collection<?> c){
        if(c.size() ==0) return "[]";
        StringBuilder result = new StringBuilder("[");
        for(Object elem : c){
            if (c.size() != 1){
                result.append("\n ");
            }
            result.append(elem);
        }

        if(c.size() != 1){
            result.append("\n ");
        }
        result.append("]");
        return result.toString();
    }

    public static void pprint(Collection<?> c){
        System.out.println(pformat(c));
    }

    public static void pprint(Object[] c){
        System.out.println(pformat(Arrays.asList(c)));
    }
}
