package io.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * @ClassName AvailableCharSets
 * @Author bin
 * @Date 2020/7/27 下午7:12
 * @Decr TODO
 * @Link TODO
 **/
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String,Charset> charSet = Charset.availableCharsets();
        Iterator<String> it = charSet.keySet().iterator();

        while (it.hasNext()){
            String cName = it.next();
            System.out.print(cName);
            Iterator aliases = charSet.get(cName).aliases().iterator();

            if(aliases.hasNext()){
                System.out.print(":");
            }
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if(aliases.hasNext()){
                    System.out.print(",");
                }
            }
            System.out.println();
        }

    }
}
