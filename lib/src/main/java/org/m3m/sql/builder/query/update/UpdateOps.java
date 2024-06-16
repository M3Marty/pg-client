package org.m3m.sql.builder.query.update;

import org.m3m.sql.builder.query.returning.FilterOrReturn;

public interface UpdateOps extends AdditionalFromTables<FilterOrReturn<UpdateQuery>>,
                                   FilterOrReturn<UpdateQuery> { }