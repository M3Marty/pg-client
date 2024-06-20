package org.m3m.sql.builder.query.insert.conflict;

import org.m3m.sql.builder.query.insert.conflict.FilterableOnConflict;

public interface OnConflictBuilder extends FilterableOnConflict {

	default FilterableOnConflict onConstraint(String constraintName) {
		appendOnConflictExpression("ON CONSTRAINT " + constraintName);
		return this;
	}

	default FilterableOnConflict fields(String...fields) {
		appendOnConflictExpression("(" + String.join(",", fields) + ")");
		return this;
	}
}