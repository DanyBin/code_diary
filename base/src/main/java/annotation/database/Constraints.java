package annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Constraints
 * @Author bin
 * @Date 2020/8/6 上午11:46
 * @Decr TODO
 *      设置约束条件
 * @Link TODO
 **/
@Target(ElementType.FIELD) // 域声明
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
    //主键
    boolean primaryKey() default false;

    boolean allowNull() default true;

    //唯一
    boolean unique() default false;
}
