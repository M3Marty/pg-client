package org.m3m.sql.builder.query.select.order;

import org.m3m.sql.builder.query.select.block.BlockableSelect;
import org.m3m.sql.builder.query.select.range.RangableSelect;

public interface SortableSelect extends BlockableSelect {

	RangableSelect orderBy(String expression);

	default RangableSelect orderBy(String...fields) {
		return orderBy(String.join(",", fields));
	}
}