package org.m3m.sql.builder.query.select;

import lombok.*;
import org.m3m.sql.builder.query.*;
import org.m3m.sql.builder.query.exception.IllegalDataSourceException;
import org.m3m.sql.builder.query.from.FromQuery;
import org.m3m.sql.builder.query.from.FromSource;

@RequiredArgsConstructor
public class SelectQuery implements FromQuery, FromSource {

	@Getter @Setter
	private Query parent;

	private final String toSelect;

	private FromQuery fromQuery;

	@Override
	public FromQuery from(FromQuery fromQuery) {
		if (this == fromQuery) {
			throw new IllegalDataSourceException("Can't select from themselves");
		}
		this.fromQuery = fromQuery;
		this.fromQuery.setParent(this);

		return this.fromQuery;
	}

	@Override
	public String build() {
		return String.format("SELECT %s FROM %s", toSelect, fromQuery.buildFromQuery());
	}

	@Override
	public String buildFromQuery() {
		return String.format("(%s)", build());
	}
}