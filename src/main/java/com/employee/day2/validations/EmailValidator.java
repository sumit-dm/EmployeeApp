package com.employee.day2.validations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {  //ValidEmail is the custom annotation, and String is the type of the field being validated (in this case, the email field).
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Override
	public void initialize(ValidEmail constraintAnnotation) { //Used for any setup that needs to be done before validation. In this case, it's empty.
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) { //Contains the logic for validating the email field. The regular expression (EMAIL_PATTERN) checks if the email adheres to a common email format. If it does, the method returns true, indicating that the email is valid.
		return email != null && EMAIL_PATTERN.matcher(email).matches(); //checks if the email adheres to a common email format. If it does, the method returns true, indicating that the email is valid.
	}
}
