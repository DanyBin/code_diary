package io.stream.basic;

import java.io.*;

/**
 * @ClassName StoringAndRecoveringData
 * @Author bin
 * @Date 2020/7/27 上午10:53
 * @Decr TODO
 *      存储与恢复数据
 * @Link TODO
 **/
public class StoringAndRecoveringData {
    private static String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/Data.txt";
    public static void main(String[] args) throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
        out.writeDouble(3.1415926);
        out.writeUTF("test");
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
