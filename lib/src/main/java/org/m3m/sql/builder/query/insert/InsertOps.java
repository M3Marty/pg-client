package org.m3m.sql.builder.query.insert;

import org.m3m.sql.builder.query.insert.conflict.AddOrOnConflictOrReturn;
import org.m3m.sql.builder.query.insert.conflict.OnConflictBuilder;

public interface InsertOps extends AddOrOnConflictOrReturn, OnConflictBuilder {

	@Override
	default OnConflictBuilder onConflict() {
		return this;
	}
}