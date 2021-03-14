package io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LockingFile
 * @Author bin
 * @Date 2020/7/28 下午7:02
 * @Decr TODO
 *      文件加锁 - 简单测试
 * @Link TODO
 **/
public class LockingFile {
    public static void main(String[] args) throws Exception {
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/files.txt";
        FileOutputStream out = new FileOutputStream(path);
        //非阻塞
        FileLock fl = out.getChannel().tryLock();
        if (fl != null) {
            System.out.println("locked file ");
            TimeUnit.MILLISECONDS.sleep(100);
            fl.release();
            System.out.println("released lock");
        }
        out.close();
    }
}
