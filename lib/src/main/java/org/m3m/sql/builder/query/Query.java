package org.m3m.sql.builder.query;

public interface Query {

	default String build() {
		Query topLevel = this;
		while (topLevel.getParent() != null) {
			topLevel = topLevel.getParent();
		}

		if (topLevel == this)
			throw new UnsupportedOperationException("Top level Query could override build()");

		return topLevel.build();
	}

	String buildExpression();

	Query getParent();
	void setParent(Query query);
}