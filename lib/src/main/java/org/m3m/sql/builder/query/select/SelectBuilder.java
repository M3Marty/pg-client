package org.m3m.sql.builder.query.select;

import org.m3m.sql.builder.query.from.FromQuery;
import org.m3m.sql.builder.query.from.FromSource;

public class SelectBuilder implements FromSource {

	private final StringBuilder selectQuery = new StringBuilder();

	public SelectBuilder entry(String expression) {
		if (selectQuery.isEmpty())
			selectQuery.append(expression);
		else
			selectQuery.append(", ").append(expression);

		return this;
	}

	public SelectQuery build() {
		return new SelectQuery(selectQuery.toString());
	}

	@Override
	public FromQuery from(FromQuery fromQuery) {
		return build().from(fromQuery);
	}
}