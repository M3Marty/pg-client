package org.m3m.sql.builder.query.update;

import org.m3m.sql.builder.Sql;
import org.m3m.sql.builder.query.ListQuery;
import org.m3m.sql.builder.query.Query;

public interface UpdateSetOpts<T extends UpdateSetOpts<?>> extends Query {

	T set(String field, String expression);

	default T set(Object dst, Object src) {
		return set(Sql.getObjectStringValue(dst), Sql.getObjectStringValue(src));
	}

	default T set(ListQuery dst, ListQuery src) {
		return set(
				String.format("(%s)", dst.buildExpression()),
				String.format("(%s)", src.buildExpression())
		);
	}
}