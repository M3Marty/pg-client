package org.m3m.sql.builder.query.select.group;

import org.m3m.sql.builder.query.select.table.TableBiOps;

public interface GroupableSelect extends TableBiOps {

	GroupingSelectOps groupBy(String expression);
}