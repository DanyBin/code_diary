package concurrency.share;

/**
 * @ClassName IntGenerator
 * @Author bin
 * @Date 2020/8/11 下午3:45
 * @Decr TODO
 *      volatile 的使用
 * @Link TODO
 **/
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel(){canceled = true;}
    public boolean isCanceled() { return canceled;}
}
