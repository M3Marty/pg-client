package org.m3m.sql.builder.query.where;

public interface WhereExpression<T extends WhereOps<?>> {

	 StringBuilder getWhereExpression();

	 @SuppressWarnings("unchecked")
	 default T appendWhereExpression(String expression) {
		 getWhereExpression().append(' ').append(expression);
		 return (T) this;
	 }
}