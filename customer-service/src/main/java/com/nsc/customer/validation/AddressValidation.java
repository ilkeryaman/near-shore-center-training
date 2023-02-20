package com.nsc.customer.validation;

import com.nsc.customer.validation.validator.AddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AddressValidator.class)
public @interface AddressValidation {
    public String message() default "city must be one of Ankara, İzmir or İstanbul!";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
