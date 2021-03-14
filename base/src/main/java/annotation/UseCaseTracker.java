package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName UseCaseTracker
 * @Author bin
 * @Date 2020/8/6 上午11:22
 * @Decr TODO
 *      注解处理器 - 用于解析带有注解的代码
 * @Link TODO
 **/
public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases,Class<?> cl) {
        for(Method m :cl.getDeclaredMethods()) {
            UseCase u = m.getAnnotation(UseCase.class);
            if(u != null) {
                System.out.println("注解UseCase ：" + u.id() + u.desc());
                useCases.remove(new Integer(u.id()));
            }
        }

        for (int i :useCases) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCases(useCases,UseCaseable.class);
    }
}
