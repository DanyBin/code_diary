package jvm.gc;

/**
 * @ClassName ReferenceCountingGC
 * @Author bin
 * @Date 2020/9/30 上午10:48
 * @Decr TODO
 *      使用计数算法的缺陷
 *   VM Args:   -XX:+PrintGC 输出GC日志
 *              -XX:+PrintGCDetails 输出GC的详细日志
 * @Link TODO
 **/
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024* 1024;

    //占用内存，一边GC日志中看清楚是否回收过
    private byte[] bigSize = new byte[2*_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;
        System.gc();
    }
}
