package io.serialization;

import java.io.*;

/**
 * @ClassName Blips
 * @Author bin
 * @Date 2020/7/29 下午12:12
 * @Decr TODO
 *      Test
 * @Link TODO
 **/
public class Blips {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String outFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/Blips.out";

        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(outFile)
        );

        out.writeObject(b1);
        out.writeObject(b2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(outFile));
        b1 = (Blip1)in.readObject();
        System.out.println(b1);
        b2 = (Blip2)in.readObject();

    }
}
