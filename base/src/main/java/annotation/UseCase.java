package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName UseCase
 * @Author bin
 * @Date 2020/8/6 上午10:52
 * @Decr TODO
 *      简单注解，用于追踪项目
 * @Link TODO
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
        public int id();
        public String desc() default "no desc";
}


