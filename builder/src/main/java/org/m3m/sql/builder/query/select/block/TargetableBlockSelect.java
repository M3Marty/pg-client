package org.m3m.sql.builder.query.select.block;

public interface TargetableBlockSelect extends BlockedSelect {

	default BlockedSelect of(String...tables) {
		getBlockExpression().append(" OF ").append(String.join(",", tables));
		return this;
	}
}