package io.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @ClassName BufferedInputFile
 * @Author bin
 * @Date 2020/7/24 下午3:33
 * @Decr TODO
 * @Link TODO
 **/
public class BufferedInputFile {
    public static String read(String fileName) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder builder = new StringBuilder();
        while ((s = in.readLine()) != null){
            builder.append(s+ "\n");
        }
        in.close();
        return builder.toString();
    }

    public static void main(String[] args)  throws IOException{
        System.out.println(read("/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/BufferedInputFile.java"));
    }
}
