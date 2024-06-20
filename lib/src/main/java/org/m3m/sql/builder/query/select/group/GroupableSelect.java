package org.m3m.sql.builder.query.select.group;

public interface GroupableSelect {

	GroupingSelectOps groupBy(String expression);
}