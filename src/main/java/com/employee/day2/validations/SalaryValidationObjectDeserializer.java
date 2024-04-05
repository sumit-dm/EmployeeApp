package com.employee.day2.validations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.employee.day2.validations.SalaryValidationObject;

import java.io.IOException;

public class SalaryValidationObjectDeserializer extends StdDeserializer<SalaryValidationObject> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalaryValidationObjectDeserializer() {
        this(null);
    }

    public SalaryValidationObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SalaryValidationObject deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Double salary = node.get("salary").asDouble();
        String gender = node.get("gender").asText();
        return new SalaryValidationObject(salary, gender);
    }
}

