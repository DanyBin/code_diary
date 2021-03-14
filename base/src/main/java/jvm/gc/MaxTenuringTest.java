package jvm.gc;

/**
 * @ClassName MaxTenuringTest
 * @Author bin
 * @Date 2020/11/5 下午12:08
 * @Decr TODO
 *      用于测试长期存活对象的进入老年代的参数设置
 *      VM参数
 *      -verbose:gc -Xms20m -Xmx20M -Xmn10 -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1（晋升老年代的判断条件）
 *      -XX:+PrintTenuringDistribution
 * @Link TODO
 **/
public class MaxTenuringTest {
    private   static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] a1 , a2, a3;
        a1 = new byte[_1MB / 4];
        a2 = new byte[4 * _1MB];
        a3 = new byte[4 * _1MB];
        a3 = null;
        a3 = new byte[4 * _1MB];
    }
}
