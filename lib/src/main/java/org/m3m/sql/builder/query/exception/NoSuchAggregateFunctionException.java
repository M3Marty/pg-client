package org.m3m.sql.builder.query.exception;

import org.m3m.sql.builder.query.validation.AggregateFunction;

import java.util.Arrays;

public class NoSuchAggregateFunctionException extends SqlException {

	public NoSuchAggregateFunctionException(String function, String...argv) {
		super(String.format("Function %s not exists. Given %s",
				function, Arrays.toString(argv)));
	}

	public NoSuchAggregateFunctionException(String function,
			AggregateFunction description, String...argv) {
		super(String.format("Function %s accepts %d arguments. Given %s",
				function, description.value().getArgsNumber(),
				Arrays.toString(argv)));
	}
}