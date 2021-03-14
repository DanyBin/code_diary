package io.compression;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @ClassName GZIPcompress
 * @Author bin
 * @Date 2020/7/28 下午7:30
 * @Decr TODO
 *      压缩测试
 * @Link TODO
 **/
public class GZIPcompress {

    public static void main(String[] args)  throws  Exception{
        String inFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/Data.txt";
        String outFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data1.gz";

        BufferedReader in = new BufferedReader(
                new FileReader(inFile)
        );

        //压缩类的使用
        BufferedOutputStream out = new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream(outFile))
        );

        System.out.println("writing file");

        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();

        System.out.println("read file");
        //压缩类的使用
        BufferedReader in2 = new BufferedReader(
               new InputStreamReader(new GZIPInputStream(new FileInputStream(outFile)))
        );

        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
    }
}
