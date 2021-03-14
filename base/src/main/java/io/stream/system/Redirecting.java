package io.stream.system;

import java.io.*;

/**
 * @ClassName Redirecting
 * @Author bin
 * @Date 2020/7/27 下午2:51
 * @Decr TODO
 *      重定向输出。 操作的是字节流
 *         将标准的输出重定向到一个文件上。
 *
 * @Link TODO
 **/
public class Redirecting {
    public static void main(String[] args) throws IOException {
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/system/Redirecting.java";
        String outPath = "/Users/bin/gitStudy/code_diary/base/src/main/resources/test.out";
        PrintStream console = System.out;

        BufferedInputStream in  = new BufferedInputStream(new FileInputStream(path));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(outPath)));

        //重定向设置
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        //重定向输出
        while ((s = br.readLine()) !=  null){
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
