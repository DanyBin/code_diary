package io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @ClassName UsingBuffers
 * @Author bin
 * @Date 2020/7/28 上午10:14
 * @Decr TODO
 *      使用缓冲器进行索引的更新，并显示齐变化
 * @Link TODO
 **/
public class UsingBuffers {
    //遍历char[] 并将1，2 /3，4 进行交换
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()){
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length*2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        //重置mark,打印出所有内容
        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());
    }
}
