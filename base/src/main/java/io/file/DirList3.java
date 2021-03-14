package io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @ClassName DirList3
 * @Author bin
 * @Date 2020/7/24 上午10:15
 * @Decr TODO
 * @Link TODO
 **/
public class DirList3 {
    public static void main(final String[] args) {
        final File path = new File("./base/src/main/java");
        String[] list = path.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(args[0]);
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String p :list){
            System.out.println(p);
        }
    }
}
