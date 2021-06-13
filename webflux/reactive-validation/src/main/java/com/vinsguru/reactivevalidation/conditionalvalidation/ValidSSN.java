package com.vinsguru.reactivevalidation.conditionalvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = SSNConstraintValidator.class)
public @interface ValidSSN {
    String message() default "{ssn.not.null}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
