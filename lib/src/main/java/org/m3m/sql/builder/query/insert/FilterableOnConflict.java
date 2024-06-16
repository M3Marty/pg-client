package org.m3m.sql.builder.query.insert;

import org.m3m.sql.builder.query.where.WhereCondition;

public interface FilterableOnConflict extends WhereCondition<OnConflict>,
                                              OnConflict { }