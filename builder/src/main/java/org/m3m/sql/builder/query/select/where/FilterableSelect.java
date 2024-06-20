package org.m3m.sql.builder.query.select.where;

import org.m3m.sql.builder.query.select.SelectQuery;
import org.m3m.sql.builder.query.where.WhereCondition;

public interface FilterableSelect extends WhereCondition<SelectFilterBuilder>,
                                          FilteredSelectOps {

	@Override
	default SelectFilterBuilder where(String expression) {
		var builder = new SelectFilterBuilder((SelectQuery) this);
		builder.appendCondition("WHERE " + expression);
		return builder;
	}
}