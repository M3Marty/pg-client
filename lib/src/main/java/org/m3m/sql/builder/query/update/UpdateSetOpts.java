package org.m3m.sql.builder.query.update;

import org.m3m.sql.builder.Sql;
import org.m3m.sql.builder.query.ListQuery;

public interface UpdateSetOpts<T extends UpdateSetOpts<?>> {

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