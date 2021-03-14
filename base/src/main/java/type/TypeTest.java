package type;

/**
 * @ClassName TypeTest
 * @Author bin
 * @Date 2020/7/7 下午2:18
 * @Decr TODO
 * @Link TODO
 **/
public class TypeTest {
    public static void main(String[] args) throws Exception {

        //创建class引用
        Class<?> typeTest = Class.forName("innerclasses.GreenhouseControls");

        //获取接口信息
        Class<?>[] interfaces = typeTest.getInterfaces();

        //创建class对象
        Object o = typeTest.newInstance();

        //获取父类
        Class<?> superclass = typeTest.getSuperclass();

        //创建Class引用，限制类型
        Class<? extends Number> integerClass = int.class;

    }
}
