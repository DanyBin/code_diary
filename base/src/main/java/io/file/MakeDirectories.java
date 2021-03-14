package io.file;

import java.io.File;

/**
 * @ClassName MakeDirectories
 * @Author bin
 * @Date 2020/7/24 下午12:02
 * @Decr TODO
 * @Link TODO
 **/
public class MakeDirectories {

    private static void fileData(File file){
        System.out.println(
                "Absolute path: "+ file.getAbsolutePath() +
                        "\n Can read: " + file.canRead() +
                        "\n Can write: " + file.canWrite() +
                        "\n getName: " + file.getName() +
                        "\n getParent: " + file.getParent() +
                        "\n getPath: " + file.getPath() +
                        "\n length: " + file.length() +
                        "\n lastModified: " + file.lastModified()
        );
        if(file.isFile()) {
            System.out.println("is file");
        }else if(file.isDirectory()) {
            System.out.println("is directory");
        }
    }

    public static void main(String[] args) {
       File old =  new File("/Users/bin/gitStudy/code_diary/base/src/main/resources/file.txt");
       File rname = new File("/Users/bin/gitStudy/code_diary/base/src/main/resources/files.txt");
       old.renameTo(rname);
       fileData(old);
       fileData(rname);

        File df = new File("/Users/bin/gitStudy/code_diary/base/src/main/resources/delete.txt");
        if(df.exists()){
            df.delete();
        }else {
            df.mkdir();
        }

    }
}
