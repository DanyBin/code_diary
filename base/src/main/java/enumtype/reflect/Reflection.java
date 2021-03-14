package enumtype.reflect;


import io.stream.process.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName Reflection
 * @Author bin
 * @Date 2020/7/31 上午11:02
 * @Decr TODO
 * @Link TODO
 **/
public class Reflection {

    public static Set<String> analyze(Class<?> enumClass){
        System.out.println("---analyzing " + enumClass + " ------");
        System.out.println("Interfaces");

        for(Type t : enumClass.getGenericInterfaces()){
            System.out.println(t);
        }

        System.out.println("base "+ enumClass.getSuperclass());
        System.out.println("Methods");

        Set<String> methods = new TreeSet<String>();
        for(Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);

        System.out.println("explore containAll (Enum) ? "  + exploreMethods.containsAll(enumMethods));
        System.out.println("explore removeAll (Enum) ? " + exploreMethods.removeAll(enumMethods));
        System.out.println(exploreMethods);

        OSExecute.command("javap Explore");
    }
}
