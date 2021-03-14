package io.nio;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MappedIO
 * @Author bin
 * @Date 2020/7/28 上午10:39
 * @Decr TODO
 *      使用内存映射文件，进行I/O的性能测试
 * @Link TODO
 **/
public class MappedIO {
    private static int numOfInts = 4000000;
    private static int numOfBufferInt = 200000;

    private abstract static class Tester {
        private String name;
        public Tester (String name) {this.name = name;}
        public void runTest() {
            System.out.println("name: "+name);
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n",duration/1.0e9);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public abstract void test() throws IOException;
    }

    static String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/temp.tmp";
    private static Tester[] testers = {
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(new File(path))
                            ));
                    for (int i=0; i < numOfInts;i ++){
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(path,"rw").getChannel();
                    IntBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    for (int i=0; i< numOfInts; i++){
                        buffer.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream read") {
                @Override
                public void test() throws IOException {
                    DataInputStream in = new DataInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(path)
                            )
                    );
                    for(int i=0; i< numOfInts;i++){
                        in.readInt();
                    }
                    in.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream(new File(path)).getChannel();
                    IntBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()).asIntBuffer();
                    while (buffer.hasRemaining()){
                        buffer.get();
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(path,"rw");
                    raf.writeInt(1);
                    for (int i=0; i< numOfBufferInt;i++){
                        raf.seek(raf.length() -4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapper Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(new File(path),"rw").getChannel();
                    IntBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    buffer.put(0);
                    for (int i=1; i< numOfBufferInt;i++){
                        buffer.put(buffer.get(i-1));
                    }
                    fc.close();
                }
            }
    };

    public static void main(String[] args) {
        for(Tester tester:testers){
            tester.runTest();
        }
    }

}
