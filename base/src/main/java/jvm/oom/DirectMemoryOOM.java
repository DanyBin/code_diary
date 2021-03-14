package jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName DirectMemoryOOM
 * @Author bin
 * @Date 2020/9/30 上午10:36
 * @Decr TODO
 *      使用unsafe分配本机内存
 *      VM Args : -Xmx20M -XX:MaxDirectMemorySize=10M  (直接内存的大小)
 * @Link TODO
 **/
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe)field.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
