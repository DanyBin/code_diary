package annotation.database;

import java.lang.annotation.Target;

/**
 * @ClassName Uniqueness
 * @Author bin
 * @Date 2020/8/6 上午11:55
 * @Decr TODO
 * @Link TODO
 **/
public @interface Uniqueness {
    //修改默认值
    Constraints constraints() default @Constraints(unique = true);
}
