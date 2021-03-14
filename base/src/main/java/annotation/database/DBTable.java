package annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName BDTable
 * @Author bin
 * @Date 2020/8/6 上午11:41
 * @Decr TODO
 *      用于注释的数据库名
 * @Link TODO
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BDTable {
    public String name() default  "";
}
