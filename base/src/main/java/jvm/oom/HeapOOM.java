package jvm.oom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName HeapOOM
 * @Author bin
 * @Date 2020/9/29 下午5:27
 * @Decr TODO
 *  VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *  设置堆的最小值与最大值 。
 * @Link TODO
 **/
public class HeapOOM {

    static class OOMObject{}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
