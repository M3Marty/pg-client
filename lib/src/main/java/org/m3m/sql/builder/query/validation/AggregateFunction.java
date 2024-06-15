package org.m3m.sql.builder.query.validation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AggregateFunction {
	AggregateFunctionType value() default AggregateFunctionType.SINGLE;
}