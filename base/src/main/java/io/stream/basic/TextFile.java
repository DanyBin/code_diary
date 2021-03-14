package io.stream.basic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @ClassName TextFile
 * @Author bin
 * @Date 2020/7/27 上午11:30
 * @Decr TODO
 *      文件读取与写入操作。
 * @Link TODO
 **/
public class TextFile extends ArrayList<String> {
    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));

            try {
                String s;
                while ((s = in.readLine()) != null){
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void write(String fileName,String text){
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.print(text);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextFile(String fileName,String splitter){
        super(Arrays.asList(read(fileName).split(splitter)));
        if(get(0).equals(""))remove(0);
    }

    public TextFile(String fileName){
        this(fileName,"\n");
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            for(String item : this){
                out.println(item);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String inFile = "/Users/bin/gitStudy/code_diary/base/src/main/java/io/stream/pipe/TextFile.java";
        String outFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/TestFile.txt";
        String outFile2 = "/Users/bin/gitStudy/code_diary/base/src/main/resources/TestFile2.txt";

        String read = read(inFile);
        write(outFile,read);


        TextFile text = new TextFile(outFile);
        text.write(outFile2);

        TreeSet<String> words = new TreeSet<String>(new TextFile(outFile,"\\W+"));

        System.out.println(words.headSet("a"));
    }
}
