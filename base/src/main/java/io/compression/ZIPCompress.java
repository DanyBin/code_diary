package io.compression;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @ClassName ZIPCompress
 * @Author bin
 * @Date 2020/7/28 下午7:50
 * @Decr TODO
 *      使用ZIP 进行多文件保存
 * @Link TODO
 **/
public class ZIPCompress {
    public static void main(String[] args) throws IOException{
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/test.zip";

        String[] files = {"/Users/bin/gitStudy/code_diary/base/src/main/resources/Data.txt"
                ,"/Users/bin/gitStudy/code_diary/base/src/main/resources/TestFile.txt"
        ,"/Users/bin/gitStudy/code_diary/base/src/main/resources/TestFile2.txt"};


        FileOutputStream f = new FileOutputStream(path);
        //压缩类型
        CheckedOutputStream csum = new CheckedOutputStream(f,new Adler32());

        ZipOutputStream zos = new ZipOutputStream(csum);

        BufferedOutputStream out = new BufferedOutputStream(zos);

        zos.setComment("A test of Java Zipping");

        //多文件写入Zip
        for (String file: files){
            System.out.println("writing file" + file);
            BufferedReader in = new BufferedReader(new FileReader(file));
            //必须调用该方法,将文件压入
            zos.putNextEntry(new ZipEntry(file));

            int c ;
            while ((c = in.read()) != -1){
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();

        System.out.println("CheckSum: " +csum.getChecksum().getValue());


        System.out.println("read zip file");
        FileInputStream in = new FileInputStream(path);
        CheckedInputStream csumi = new CheckedInputStream(in,new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);

        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            System.out.println("read file " + ze);

            int x;
            while ((x = bis.read()) != -1){
                System.out.write(x);
            }
        }

        System.out.println("CheckSum: " +csumi.getChecksum().getValue());
        bis.close();

        //另外一种打开Zip文件的方式
        ZipFile zf = new ZipFile(path);
        Enumeration e = zf.entries();
        while (e.hasMoreElements()){
            ZipEntry ze2 = (ZipEntry)e.nextElement();
            System.out.println("file : " + ze2);
        }
    }
}
