package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.from.FromQuery;
import org.m3m.sql.builder.query.from.FromTableRequest;
import org.m3m.sql.builder.query.select.*;

@UtilityClass
public class Sql {

	public String field(String field) {
		return field;
	}

	public String field(String field, String alias) {
		return field + " AS " + alias;
	}

	public String aggregation(String function, String...argv) {
		return AggregateQuery.buildFunction(function, argv);
	}

	public String aggregation(String function, String alias, String...argv) {
		return aggregation(function, argv) + " AS " + alias;
	}

	public FromQuery table(String table) {
		return new FromTableRequest(table);
	}

	public SelectQuery select(String toSelect) {
		return new SelectQuery(toSelect);
	}

	public SelectBuilder select() {
		return new SelectBuilder();
	}

	public SelectQuery selectAll() {
		return new SelectQuery("*");
	}

	public AggregateQuery count() {
		return new AggregateQuery(AggregateQuery.COUNT, "*");
	}

	public AggregateQuery count(String expression) {
		return new AggregateQuery(AggregateQuery.COUNT, expression);
	}

	public AggregateQuery sum(String expression) {
		return new AggregateQuery(AggregateQuery.SUM, expression);
	}
}