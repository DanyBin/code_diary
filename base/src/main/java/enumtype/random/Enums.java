package enumtype.random;

import java.util.Random;

/**
 * @ClassName Enums
 * @Author bin
 * @Date 2020/7/31 下午12:02
 * @Decr TODO
 *      随机选取  enum元素
 * @Link TODO
 **/
public class Enums {
    private static Random random = new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[random.nextInt(values.length)];
    }
}
