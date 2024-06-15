package org.m3m.sql.builder.query;

import org.m3m.sql.builder.query.from.FromQuery;
import org.m3m.sql.builder.query.from.FromSource;

public interface InAlias extends FromSource {

	default FromQuery in(FromQuery fromQuery) {
		return from(fromQuery);
	}
}