package io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName LargeMappedFiles
 * @Author bin
 * @Date 2020/7/28 上午10:25
 * @Decr TODO
 *      内存映射文件
 * @Link TODO
 **/
public class LargeMappedFiles {

    static int length = 0x8FFFFFF; //128MB
    static int length2 = 0x100000; //1MB

    public static void main(String[] args) throws IOException {
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/rtest.dat";
        //创建读写的内存映射
        MappedByteBuffer out = new RandomAccessFile(path,"rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE,0,length2);

        for (int i=0; i < length2; i++){
            out.put((byte)'x');
        }

        System.out.println("Finished writing");
        for(int i=0; i< length2/2 ;i++){
            System.out.println((char)out.get(i));
        }

    }
}
