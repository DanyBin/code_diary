package generics.functional;

/**
 * @ClassName Collector
 * @Author bin
 * @Date 2020/7/14 上午11:07
 * @Decr TODO
 * @Link TODO
 **/
public interface Collector<T> extends UnaryFunction<T,T>{
    T result();
}
