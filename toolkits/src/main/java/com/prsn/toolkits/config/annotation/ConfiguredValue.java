package com.prsn.toolkits.config.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
* Annotation for values to be loaded from the configuration file.
* <p>Example:</p>
* <pre>
* {@literal @}ConfiguredBoolean("db.enabled")
* private boolean enabled;
*
* {@literal @}ConfiguredBoolean("db.enabled")
* private Provider&lt;Boolean&gt; enabled;
* </pre>
*/
@Qualifier
@Retention(RUNTIME)
@Target({ METHOD, FIELD, PARAMETER, TYPE })
public @interface ConfiguredValue {

  /**
   * @return Value name.
   *
   * */
    @Nonbinding
    String value();

    /**
    * @return Default value as string.
    */
    @Nonbinding
    String def() default StringUtils.EMPTY;

    /**
     * @return Required value flag.
     */
    @Nonbinding
    boolean required() default true;
}

