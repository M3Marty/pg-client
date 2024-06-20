package org.m3m.sql.builder.query.select;

import org.m3m.sql.builder.query.As;
import org.m3m.sql.builder.query.Query;

public interface SelectAlias extends As<String>, Query {

	@Override
	default String as(String alias) {
		return buildExpression() + " AS " + alias;
	}
}