package io.stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @ClassName FormattedMemoryInput
 * @Author bin
 * @Date 2020/7/24 下午4:23
 * @Decr TODO
 * @Link TODO
 **/
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try {
            String fileName = "/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/FormattedMemoryInput.java";
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read(fileName).getBytes()));
            while(true){
                System.out.println((char)in.readByte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
