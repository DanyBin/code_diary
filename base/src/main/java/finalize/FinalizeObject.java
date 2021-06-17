package finalize;

/**
 * @ClassName FinalizeObject
 * @Author bin
 * @Date 2021/4/6 下午8:41
 * @Decr TODO
 *          在jvm GC时，会包装为 FinalReference对象。方便gc回收
 *          GC日志中对 [FinalReference, 323319 refs, 0.1970609 secs] 的回收
 *
 *
 * @Link TODO
 **/
public class FinalizeObject  {
    @Override
    protected void finalize() throws Throwable {
       //做关闭出炉
    }
}
