package io.file;

import java.io.File;
import java.util.Arrays;

/**
 * @ClassName DirList
 * @Author bin
 * @Date 2020/7/24 上午10:04
 * @Decr TODO
 * @Link TODO
 **/
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list = path.list();
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list){
            System.out.println(dirItem);
        }
    }
}
