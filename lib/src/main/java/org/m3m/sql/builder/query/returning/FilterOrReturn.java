package org.m3m.sql.builder.query.returning;

import org.m3m.sql.builder.query.where.WhereConditionOrCursor;

public interface FilterOrReturn<T> extends WhereConditionOrCursor<T>,
                                           Returning { }