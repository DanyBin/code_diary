package io.stream.basic;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @ClassName UsingRandomAccessFile
 * @Author bin
 * @Date 2020/7/27 上午11:01
 * @Decr TODO
 *      随机读写文件
 * @Link TODO
 **/
public class UsingRandomAccessFile {
    static String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/rtest.dat";
    static void display() throws IOException {
        //"只读" -> r
        RandomAccessFile rf = new RandomAccessFile(file,"r");
        for(int i=0; i< 7 ;i ++) {
            System.out.println("value " + i + ": "+ rf.readDouble());
        }
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args)  throws IOException{
        //"读写"——> rw
        RandomAccessFile rf = new RandomAccessFile(file,"rw");
        for (int i=0; i < 7 ; i++){
            rf.writeDouble(i*1.4);
        }
        rf.writeUTF("The end of the file");
        rf.close();
        display();

        rf = new RandomAccessFile(file,"rw");
        //位置
        rf.seek(5*8);
        rf.writeDouble(47.00001);
        rf.close();
        display();
    }
}
