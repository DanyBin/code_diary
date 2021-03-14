package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2020/8/6 上午10:37
 * @Decr TODO
 *     定义注解-
 *      注解也会被编译成class文件
 * @Link TODO
 **/
@Target(ElementType.METHOD) // 注解应用的地方（例如 一个方法 或者一个域）
@Retention(RetentionPolicy.RUNTIME) //注解在哪一个级别应用。 （源代码、类文件、运行时）
public @interface Test {}
