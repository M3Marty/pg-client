package org.m3m.sql.builder.query.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AggregateFunctionType {
	SINGLE(1, "(%s)"), PAIR(2, "(%s, %s)");

	private final int argsNumber;
	private final String format;
}
