package com.au.jiro.controller.call;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD })
@Retention(RUNTIME)
public @interface UserLog {
    String name() default "";

    boolean unique() default false;

    boolean nullable() default true;

    boolean insertable() default true;

    boolean updatable() default true;

    String columnDefinition() default "";

    String table() default "";

    int length() default 255;

    int precision() default 0;

    int scale() default 0;
}
