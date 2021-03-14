package jvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RuntimeConstantPoolOOM
 * @Author bin
 * @Date 2020/9/29 下午8:17
 * @Decr TODO
 * VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M
 * sh 设置 永久代的大小
 * @Link TODO
 **/
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        //让6M的PermSize产生OOM
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }

    }
}
