package generics;

import type.CountedInteger;

/**
 * @ClassName BasicGenerator
 * @Author bin
 * @Date 2020/7/8 下午6:59
 * @Decr TODO
 * @Link TODO
 **/
public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;
    public BasicGenerator(Class<T> type){this.type = type;}
    public T next() {
        try {
            //创建对象
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

        //创建一个泛型的Class,并进行类型推断
    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<T>(type);
    }

    public static void main(String[] args) {
        Generator<CountedInteger> countedIntegerGenerator = BasicGenerator.create(CountedInteger.class);
        for(int i=0;i < 4;i++){
            System.out.println(countedIntegerGenerator.next());
        }
    }
}
