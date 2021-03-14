package io.stream.system;

import java.io.PrintWriter;

/**
 * @ClassName ChangeSystemOut
 * @Author bin
 * @Date 2020/7/27 下午12:17
 * @Decr TODO
 * @Link TODO
 **/
public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out,true);
        out.println("tttt");
    }
}
