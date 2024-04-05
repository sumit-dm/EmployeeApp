package com.employee.day2.validations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SalaryValidator.class)
public @interface ValidSalary {

	String message() default "Invalid salary";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}
