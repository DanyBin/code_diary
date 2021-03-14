package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName GetChannel
 * @Author bin
 * @Date 2020/7/27 下午4:39
 * @Decr TODO
 *      获取Channel的方式
 *
 * @Link TODO
 **/
public class GetChannel {
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception{
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data.txt";
        FileChannel fc = new FileOutputStream(path).getChannel();
        fc.write(ByteBuffer.wrap("this is getChannel test".getBytes()));
        fc.close();


        fc = new RandomAccessFile(path,"rw").getChannel();
        //移动到尾部
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();

        fc = new FileInputStream(path).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
        fc.read(byteBuffer);
        //读取操作时，必须告知程序
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println((char)byteBuffer.get());
        }
    }
}
