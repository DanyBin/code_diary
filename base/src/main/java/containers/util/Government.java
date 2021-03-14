package containers.util;

import generics.Generator;

/**
 * @ClassName Government
 * @Author bin
 * @Date 2020/7/21 下午12:01
 * @Decr TODO
 * @Link TODO
 **/
public class Government implements Generator<String> {

    String[] strings = "qwer ooad flmlz nvblo erplqa".split(" ");
    private int index;
    public String next() {
        return strings[index++];
    }
}
