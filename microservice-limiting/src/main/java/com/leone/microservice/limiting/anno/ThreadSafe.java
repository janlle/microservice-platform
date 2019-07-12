package com.leone.microservice.limiting.anno;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-12
 **/
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafe {
}
