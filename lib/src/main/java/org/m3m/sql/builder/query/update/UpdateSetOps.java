package org.m3m.sql.builder.query.update;

import org.m3m.sql.builder.Sql;
import org.m3m.sql.builder.query.ListQuery;
import org.m3m.sql.builder.query.Query;

import java.util.List;

public interface UpdateSetOps extends UpdateOps, Query {

	List<String> getSetExpressions();

	default UpdateSetOps set(String field, String expression) {
		getSetExpressions().add(field + " = " + expression);
		return this;
	}

	default UpdateSetOps set(Object dst, Object src) {
		return set(Sql.getObjectStringValue(dst), Sql.getObjectStringValue(src));
	}

	default UpdateOps set(ListQuery dst, ListQuery src) {
		return set(
				String.format("(%s)", dst.buildExpression()),
				String.format("(%s)", src.buildExpression())
		);
	}
}