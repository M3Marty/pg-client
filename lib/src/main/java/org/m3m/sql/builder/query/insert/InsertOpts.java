package org.m3m.sql.builder.query.insert;

public interface InsertOpts extends AddOrOnConflictOrReturn, OnConflictBuilder {

	@Override
	default OnConflictBuilder onConflict() {
		return this;
	}
}