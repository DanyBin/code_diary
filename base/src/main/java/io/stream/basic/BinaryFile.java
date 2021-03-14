package io.stream.basic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName BinaryFile
 * @Author bin
 * @Date 2020/7/27 下午12:00
 * @Decr TODO
 * @Link TODO
 **/
public class BinaryFile {
    public static byte[] read(File bfile) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(
                new FileInputStream(bfile)
        );

        byte[] data = new byte[bf.available()];
        bf.read(data);
        bf.close();
        return data;
    }

    public static byte[] read(String bfile) throws IOException {
        return read(new File(bfile).getAbsolutePath());
    }
}
