package thredSynchronize.build;

/**
 * @ClassName Computable
 * @Author bin
 * @Date 2020/5/10 下午4:21
 * @Decr TODO
 *   声明函数 compute ，输入类型A，输出类型V，
 * @Link TODO
 **/
public interface Computable<A,V> {
    V compute(A arg) throws  InterruptedException;
}
