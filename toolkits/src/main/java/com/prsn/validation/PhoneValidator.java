package com.prsn.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @author プロソニーPRSN
 */

public class PhoneValidator implements ConstraintValidator<Phone, CharSequence> {

    @Override
    public void initialize(Phone parameters) {
        size = parameters.value();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence ==  null){
            return true;
        }
        int length = charSequence.length();
        return length == size;
    }

    private byte size;
}
