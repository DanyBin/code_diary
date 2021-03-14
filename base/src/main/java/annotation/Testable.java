package annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName Testable
 * @Author bin
 * @Date 2020/8/6 上午10:41
 * @Decr TODO
 * @Link TODO
 **/
public class Testable {
    public void execute(){
        System.out.println("execute");
    }
    @Test void testExecute(){execute();}

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Testable.class.newInstance().testExecute();

        //通过反射的方式，读取注解
        Test annotation = Testable.class.getAnnotation(Test.class);
        System.out.println(annotation);

    }
}
