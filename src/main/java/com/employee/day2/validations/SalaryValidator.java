package com.employee.day2.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class SalaryValidator implements ConstraintValidator<ValidSalary, SalaryValidationObject>  {
  
	@Override
    public void initialize(ValidSalary constraintAnnotation) {
    }

    @Override
    public boolean isValid(SalaryValidationObject value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        double salary = value.getSalary();
        String gender = value.getGender();

        if ("male".equalsIgnoreCase(gender)) {
            if (salary < 20 || salary > 120) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("For male employees, the valid range is 20 to 120")
                        .addConstraintViolation();
                return false;
            }
        } else if ("female".equalsIgnoreCase(gender)) {
            if (salary < 20 || salary > 100) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("For female employees, the valid range is 20 to 100")
                        .addConstraintViolation();
                return false;
            }
        } else {
            // Invalid gender
            return false;
        }

        return true;
    }
	
}
