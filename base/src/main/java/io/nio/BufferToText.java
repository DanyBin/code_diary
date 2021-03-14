package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @ClassName BufferToText
 * @Author bin
 * @Date 2020/7/27 下午5:13
 * @Decr TODO
 *      转换操作
 *          对应字节与字符串之间的转换
 * @Link TODO
 **/
public class BufferToText {
    public static void main(String[] args) throws IOException{
        String path2 = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data2.txt";
        FileChannel fc = new FileOutputStream(path2).getChannel();
        fc.write(ByteBuffer.wrap("some type".getBytes()));
        fc.close();

        //方式1
        fc = new FileInputStream(path2).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        //方式2 - 反解码
        buffer.rewind();  //返回数据的开头
        String encoding = System.clearProperty("file.encoding");
        System.out.println(Charset.forName(encoding).decode(buffer));

        //方式3 -
        fc = new FileOutputStream(path2).getChannel();
        fc.write(ByteBuffer.wrap("some type".getBytes("UTF-16BE")));
        fc.close();

        fc = new FileInputStream(path2).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

    }
}
