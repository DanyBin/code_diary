package io.stream.basic;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName TestEOF
 * @Author bin
 * @Date 2020/7/24 下午4:37
 * @Decr TODO
 *     字节读取
 * @Link TODO
 **/
public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/TestEOF.java")));
        while (in.available() != 0){
            System.out.println((char)in.readByte());
        }
    }
}
