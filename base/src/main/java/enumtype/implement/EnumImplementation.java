package enumtype.implement;

import generics.Generator;

/**
 * @ClassName EnumImplementation
 * @Author bin
 * @Date 2020/7/31 上午11:57
 * @Decr TODO
 * @Link TODO
 **/
public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.println(rg.next() + ",");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.PUNCHY;
        for(int i=0 ;i < 10;i++){
            printNext(cc);
        }
    }
 }
