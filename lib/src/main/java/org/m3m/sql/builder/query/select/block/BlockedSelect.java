package org.m3m.sql.builder.query.select.block;

import org.m3m.sql.builder.query.select.SelectAlias;

public interface BlockedSelect extends SelectAlias {

	StringBuilder getBlockExpression();

	default SelectAlias noWait() {
		getBlockExpression().append(" NOWAIT");
		return this;
	}

	default SelectAlias skipLocked() {
		getBlockExpression().append(" SKIP LOCKED");
		return this;
	}
}