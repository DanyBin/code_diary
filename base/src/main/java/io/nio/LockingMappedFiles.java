package io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @ClassName LockingMappedFiles
 * @Author bin
 * @Date 2020/7/28 下午7:09
 * @Decr TODO
 *      对映射文件部分加锁
 * @Link TODO
 **/
public class LockingMappedFiles {
    static final int Length = 0xC00000; //12MB
    static FileChannel fc;

    private static class LockAndModify extends Thread {
        private ByteBuffer buffer;
        private int start,end;

        LockAndModify(ByteBuffer buffer,int start, int end){
            this.start = start;
            this.end = end;
            buffer.limit(end);
            buffer.position(start);
            this.buffer = buffer.slice();
            start();
        }

        @Override
        public void run(){
            //独占锁
            try {
                FileLock fl = fc.lock(start,end,false);
                System.out.println("locked: "+ start + " to " + end);
                while (buffer.position() < buffer.limit() - 1){
                    buffer.put((byte)(buffer.get() + 1));
                }
                fl.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/test.out";
        fc = new RandomAccessFile(path,"rw").getChannel();

        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE,0,Length);
        for (int i=0; i < Length ; i++){
            out.put((byte)'x');
        }
        //并行访问文件系统
        new LockAndModify(out,0,0 + Length/3);
        new LockAndModify(out,Length/2,Length/2+Length/4);
    }
}

