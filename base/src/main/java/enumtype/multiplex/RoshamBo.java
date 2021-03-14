package enumtype.multiplex;

import enumtype.random.Enums;

/**
 * @ClassName RoshamBo
 * @Author bin
 * @Date 2020/8/5 上午10:37
 * @Decr TODO
 * @Link TODO
 **/
public class RoshamBo {
    /**
     * 两个对象相互 比较
     * @param a
     * @param b
     * @param <T>
     */
    public static <T extends Competitor<T>> void match(T a, T b){
        System.out.println(a  + " vs. " + b + " : " + a.compete(b));
    }

    /**
     *
     * @param tClass
     * @param size
     * @param <T>
     */
    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> tClass,int size) {
        for(int i=0; i< size; i++){
            T random = Enums.random(tClass);
            T random1 = Enums.random(tClass);
            match(random,random1);
        }
    }
}
