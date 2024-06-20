package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.ValueQuery;
import org.m3m.sql.builder.query.ValueQueryAs;

@UtilityClass
public class SqlFunctions {

	ValueQueryAs generateSeries(int from, int to) {
		return new ValueQueryAs(String.format("generate_series(%d,%d)", from, to));
	}

	ValueQueryAs sum(String expression) {
		return new ValueQueryAs(String.format("sum(%s)", expression));
	}

	ValueQueryAs count(String expression) {
		return new ValueQueryAs(String.format("count(%s)", expression));
	}

	ValueQueryAs countAll() {
		return new ValueQueryAs("count(*)");
	}
}