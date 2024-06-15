package org.m3m.sql.builder.query.returning;

import org.m3m.sql.builder.query.delete.DeleteQuery;
import org.m3m.sql.builder.query.returning.Returning;
import org.m3m.sql.builder.query.where.WhereConditionOrCursor;

public interface FilterOrReturn<T> extends WhereConditionOrCursor<T>,
                                        Returning { }