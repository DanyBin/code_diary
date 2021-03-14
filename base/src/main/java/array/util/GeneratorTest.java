package array.util;

import generics.Generator;

/**
 * @ClassName GeneratorTest
 * @Author bin
 * @Date 2020/7/20 下午8:15
 * @Decr TODO
 * @Link TODO
 **/
public class GeneratorTest {
    public static int size = 10;
    public static void test(Class<?> cl){
        for(Class<?> type:cl.getClasses()){
            System.out.println("type name :"+ type.getSimpleName());

            try {
                Generator<?> g = (Generator<?>)type.newInstance();
                for(int i=0; i < size ;i ++){
                    System.out.println(g.next() + " ");
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        test(CountingGenerator.class);
    }
}
