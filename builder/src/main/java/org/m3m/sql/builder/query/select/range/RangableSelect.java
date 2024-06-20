package org.m3m.sql.builder.query.select.range;

import org.m3m.sql.builder.query.select.block.BlockableSelect;

public interface RangableSelect extends BlockableSelect {

	StringBuilder getRangeExpression();

	default BlockableSelect fetch(int offset, int rows) {
		getRangeExpression().append("OFFSET ").append(offset)
				.append(" FETCH ").append(rows).append(" ONLY");
		return this;
	}

	default BlockableSelect fetch(int rows) {
		getRangeExpression().append("FETCH ").append(rows).append(" ONLY");
		return this;
	}

	default BlockableSelect limit(int offset, int limit) {
		getRangeExpression().append("LIMIT ").append(limit)
				.append(" OFFSET ").append(offset);
		return this;
	}

	default BlockableSelect limit(int limit) {
		getRangeExpression().append("LIMIT ").append(limit);
		return this;
	}

	default BlockableSelect offset(int offset) {
		getRangeExpression().append("OFFSET ").append(offset);
		return this;
	}
}