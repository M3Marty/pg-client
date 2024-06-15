package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.query.*;

public class WhereQuery<T> extends ValueQuery implements WhereOps<WhereQuery<T>>,
                                                      Then<T> {

	public WhereQuery(Query parent, String value) {
		super(value);
		setParent(parent);
	}

	@Override
	public void appendCondition(String expression) {
		value += expression;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T then() {
		return (T) getParent();
	}
}