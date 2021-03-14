package io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @ClassName DirList2
 * @Author bin
 * @Date 2020/7/24 上午10:10
 * @Decr TODO
 * @Link TODO
 **/
public class DirList2 {
    public static FilenameFilter filter (final String regex){
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        File path = new File("./base/src/main/java");
        String[] list =path.list(filter("^io"));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String p:list){
            System.out.println(p);
        }
    }
}
