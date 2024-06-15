package org.m3m.sql.builder.query.from;

import org.m3m.sql.builder.query.Query;

public interface FromQuery extends Query {

	String buildFromQuery();
}