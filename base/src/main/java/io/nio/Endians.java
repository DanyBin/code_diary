package io.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @ClassName Endians
 * @Author bin
 * @Date 2020/7/27 下午7:44
 * @Decr TODO
 *      测试-字节存放次序 导致数据的变化
 * @Link TODO
 **/
public class Endians {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[12]);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));

        buffer.rewind();
        //指定字节排序 高位优先
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));


        buffer.rewind();
        //指定字节排序 低位优先
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
    }
}
