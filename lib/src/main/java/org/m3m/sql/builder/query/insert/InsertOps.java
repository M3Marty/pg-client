package org.m3m.sql.builder.query.insert;

public interface InsertOps extends AddOrOnConflictOrReturn, OnConflictBuilder {

	@Override
	default OnConflictBuilder onConflict() {
		return this;
	}
}