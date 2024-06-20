package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.ValueQuery;
import org.m3m.sql.builder.query.ValueQueryAs;

@UtilityClass
public class SqlFunctions {

	ValueQuery interval(String kind, int amount) {
		return new ValueQuery(String.format("INTERVAL '%d %s'", amount, kind));
	}

	ValueQuery hours(int amount) {
		return interval("hours", amount);
	}

	ValueQueryAs generateSeries(int from, int to) {
		return new ValueQueryAs(String.format("generate_series(%d,%d)", from, to));
	}

	ValueQueryAs sum(String expression) {
		return new ValueQueryAs(String.format("SUM(%s)", expression));
	}

	ValueQueryAs avg(String expression) {
		return new ValueQueryAs(String.format("AVG(%s)", expression));
	}

	ValueQueryAs count(String expression) {
		return new ValueQueryAs(String.format("COUNT(%s)", expression));
	}

	ValueQueryAs countAll() {
		return new ValueQueryAs("COUNT(*)");
	}
}