package io.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * @ClassName FileOutputShortcut
 * @Author bin
 * @Date 2020/7/24 下午5:19
 * @Decr TODO
 * @Link TODO
 **/
public class FileOutputShortcut {
    static String file = "/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/FileOutputShortcut.java";
    static String outFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/files.txt";
    public static void main(String[] args)  throws IOException{
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(file)));
        PrintWriter out = new PrintWriter(outFile);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null){
            out.println(lineCount++ + " " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(outFile));
    }
}
