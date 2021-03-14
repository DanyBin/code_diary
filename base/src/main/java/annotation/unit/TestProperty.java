package annotation.unit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName TestProperty
 * @Author bin
 * @Date 2020/8/10 下午4:13
 * @Decr TODO
 * @Link TODO
 **/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestProperty {
}
