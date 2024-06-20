package org.m3m.sql.builder.query.select.block;

import org.m3m.sql.builder.query.Query;

public interface BlockedSelect extends Query {

	StringBuilder getBlockExpression();

	default String noWait() {
		getBlockExpression().append(" NOWAIT");
		return this.build();
	}

	default String skipLocked() {
		getBlockExpression().append(" SKIP LOCKED");
		return this.build();
	}
}