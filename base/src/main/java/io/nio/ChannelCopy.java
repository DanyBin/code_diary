package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName ChannelCopy
 * @Author bin
 * @Date 2020/7/27 下午5:04
 * @Decr TODO
 * @Link TODO
 **/
public class ChannelCopy {
    public static void main(String[] args) throws IOException {
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data.txt";
        String path2 = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data2.txt";
        FileChannel in = new FileInputStream(path).getChannel(),
                    out = new FileOutputStream(path2).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) != -1){
            //准备 被写入的操作
            buffer.flip();
            out.write(buffer);

            //准备，被读取的操作
            buffer.clear();
        }

    }
}
