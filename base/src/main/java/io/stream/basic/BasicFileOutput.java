package io.stream;

import java.io.*;

/**
 * @ClassName BasicFileOutput
 * @Author bin
 * @Date 2020/7/24 下午4:43
 * @Decr TODO
 * @Link TODO
 **/
public class BasicFileOutput {
    private static String file = "/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/BasicFileOutput.java";
    private static String outFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/files.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(file)));

        //缓存
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

        int lineCount =1;
        String s;
        while ((s = in.readLine()) != null){
            out.println(lineCount++ + " " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(outFile));
    }
}
