package io.stream.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName Echo
 * @Author bin
 * @Date 2020/7/27 下午12:12
 * @Decr TODO
 *      标准I/O
 * @Link TODO
 **/
public class Echo {
    public static void main(String[] args) throws IOException{
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = stdin.readLine()) != null && s.length() != 0){
            System.out.println(s);
        }
    }
}
