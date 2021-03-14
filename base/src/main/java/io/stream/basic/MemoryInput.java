package io.stream;

import java.io.IOException;
import java.io.StringReader;

/**
 * @ClassName MemoryInput
 * @Author bin
 * @Date 2020/7/24 下午4:19
 * @Decr TODO
 * @Link TODO
 **/
public class MemoryInput {
    public static void main(String[] args) throws IOException{
        StringReader in = new StringReader(BufferedInputFile.read("/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/MemoryInput.java"));
        int c ;
        while ((c = in.read()) != -1){
            System.out.println((char)c);
        }

    }
}
