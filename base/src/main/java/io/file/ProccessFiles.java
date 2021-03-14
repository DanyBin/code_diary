package io.file;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName ProccessFiles
 * @Author bin
 * @Date 2020/7/24 上午11:10
 * @Decr TODO
 *      策略模式
 *          通过Strategy 来处理 File
 * @Link TODO
 **/
public class ProccessFiles {
    public interface Strategy {
        void proccess(File file);
    }

    private Strategy strategy;
    private String ext;
    public ProccessFiles(Strategy strategy,String ext){
        this.strategy = strategy;
        this.ext = ext;
    }

    public void proccessDirectoryTree(File root) throws IOException{
        for(File file : Directory.walk(root.getAbsolutePath(),".*\\."+ext)){
            strategy.proccess(file.getCanonicalFile());
        }
    }

    public void start(String[] args){
        try {
            if (args.length == 0){
                proccessDirectoryTree(new File("."));
            }else {
                for (String arg : args){
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()){
                        proccessDirectoryTree(fileArg);
                    } else {
                        if(arg.endsWith("."+ext)){
                            arg += "."+ext;
                        }
                        strategy.proccess(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ProccessFiles(new Strategy(){
            public void proccess(File file) {
                System.out.println(file);
            }
        },"java").start(args);
    }
}
