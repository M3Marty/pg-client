package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.query.Query;

public interface WhereCondition<T> extends Query {

	WhereQuery<T> setWhereQuery(WhereQuery<T> whereQuery);

	default WhereQuery<T> where(String condition) {
		return setWhereQuery(new WhereQuery<>(this.query(), condition));
	}

	default WhereQuery<T> where(String operand, String operator) {
		return where(operand + operator);
	}
}