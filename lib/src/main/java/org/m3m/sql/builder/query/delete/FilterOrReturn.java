package org.m3m.sql.builder.query.delete;

import org.m3m.sql.builder.query.returning.Returning;
import org.m3m.sql.builder.query.where.WhereConditionOrCursor;

public interface FilterOrReturn extends WhereConditionOrCursor<DeleteQuery>,
                                        Returning { }