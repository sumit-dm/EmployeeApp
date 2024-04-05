package com.employee.day2.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD }) //specifies the types of program elements (fields and methods in this case) to which the custom annotation can be applied.
@Retention(RetentionPolicy.RUNTIME)//retention policy for the annotation. In this case, it is set to RUNTIME, meaning that the annotation information should be retained and accessible at runtime.
@Constraint(validatedBy = EmailValidator.class) //specifies the validator class (EmailValidator.class) that will be used to perform the actual validation for this custom annotation. The EmailValidator class is expected to implement the ConstraintValidator interface.
public @interface ValidEmail { //This line declares the custom annotation named ValidEmail. Annotations in Java are declared using the @interface keyword.

	String message() default "Invalid email address";

	Class<?>[] groups() default {}; //This line defines an element named groups, which is an array of group classes. This is a standard element in validation annotations but is not utilized in this example. Groups are used for grouping constraints.

	Class<? extends Payload>[] payload() default {}; //This line defines an element named payload, which is an array of payload classes. Payload provides additional information about validation. Like groups, it is not used in this example.
}
