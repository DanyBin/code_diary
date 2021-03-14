package io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @ClassName IntBufferDemo
 * @Author bin
 * @Date 2020/7/27 下午7:26
 * @Decr TODO
 *      使用IntBuffer 操作ByteBuffer的int数据
 *      视图缓冲器
 * @Link TODO
 **/
public class IntBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        IntBuffer intBuffer = buffer.asIntBuffer();

        intBuffer.put(new int[]{1,2,4,5,5});
        System.out.println(intBuffer.get(3));

        intBuffer.put(3,18);
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            int i = intBuffer.get();
            System.out.println(i);
        }
    }
}
