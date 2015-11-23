package javax.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface InjectedResource {

    String key() default "";

    String[] args() default {};

    String defaultValue() default "";

    String format() default "";
}
