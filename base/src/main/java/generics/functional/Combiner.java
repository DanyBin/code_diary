package generics.functional;

/**
 * @ClassName Combiner
 * @Author bin
 * @Date 2020/7/14 上午11:06
 * @Decr TODO
 *      合并接口
 * @Link TODO
 **/
public interface Combiner<T> {
    T combine(T x,T y);
}
