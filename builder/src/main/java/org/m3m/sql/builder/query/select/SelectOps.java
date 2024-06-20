package org.m3m.sql.builder.query.select;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.group.GroupableSelect;
import org.m3m.sql.builder.query.select.where.FilterableSelect;

public interface SelectOps extends SelectAlias, FilterableSelect, GroupableSelect { }