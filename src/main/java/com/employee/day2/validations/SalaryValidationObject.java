package com.employee.day2.validations;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable //This annotation is used to indicate that an entity contains the embedded fields of another class.
public class SalaryValidationObject {
	private Double salary;
    private String gender;

    public SalaryValidationObject() {
        // Default constructor
    }

    // Constructor with arguments
    public SalaryValidationObject(Double salary, String gender) {
        this.salary = salary;
        this.gender = gender;
    }
}
