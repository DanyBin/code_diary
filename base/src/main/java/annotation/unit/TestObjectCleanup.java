package annotation.unit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName TestObjectCleanup
 * @Author bin
 * @Date 2020/8/10 下午4:12
 * @Decr TODO
 * @Link TODO
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestObjectCleanup {
}
