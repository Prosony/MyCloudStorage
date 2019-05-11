package com.prsn.validation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/**
 * @author プロソニーPRSN
 */

@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneValidator.class})
public @interface Phone {

    String message() default "Phone must contain 10 symbols"; //TODO all message must get from DB. Rewrite this late

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    byte value() default 10;
}