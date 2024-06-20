package org.m3m.sql.builder.query.select.block;

import org.m3m.sql.builder.query.Query;

public interface BlockableSelect extends Query {

	StringBuilder getBlockExpression();

	default TargetableBlockSelect blockForUpdate() {
		getBlockExpression().append("FOR UPDATE");
		return (TargetableBlockSelect) this;
	}

	default TargetableBlockSelect blockForNoKeyUpdate() {
		getBlockExpression().append("FOR NO KEY UPDATE");
		return (TargetableBlockSelect) this;
	}

	default TargetableBlockSelect blockForShare() {
		getBlockExpression().append("FOR SHARE");
		return (TargetableBlockSelect) this;
	}

	default TargetableBlockSelect blockForKeyShare() {
		getBlockExpression().append("FOR KEY SHARE");
		return (TargetableBlockSelect) this;
	}
}