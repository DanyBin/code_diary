package io.file;

import io.util.PPrint;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName Directory
 * @Author bin
 * @Date 2020/7/24 上午10:33
 * @Decr TODO
 * @Link TODO
 **/
public final class Directory {
    public static File[] local(File dir,final String regex){
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path,final String regex){
        return local(new File(path),regex);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other){
            this.files.addAll(other.files);
            this.dirs.addAll(other.dirs);
        }

        @Override
        public String toString(){
            return "dirs: " + PPrint.pformat(dirs) +"    Files: "+ PPrint.pformat(files);
        }
    }

    static TreeInfo recureseDirs(File startDir,String regex){
        TreeInfo result = new TreeInfo();

        for(File item:startDir.listFiles()){
            if(item.isDirectory()){
                result.dirs.add(item);
            }else {
                if(item.getName().matches(regex)){
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static TreeInfo walk(String start,String regex){
        return recureseDirs(new File(start),regex);
    }

    public static TreeInfo walk(File file,String regex){
        return recureseDirs(file,regex);
    }

    public static TreeInfo walk(String start){
        return recureseDirs(new File(start),".*");
    }

    public static void main(String[] args) {
        System.out.println(walk("."));
    }
}
