package edu.nyu.cs9053.homework6;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: blangel
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Safe {

    static final long INVALID = 0L;

    /**
     * @return the deposit-safe's password
     */
    long password() default INVALID;

}
