package org.m3m.sql.builder.query.update;

import org.m3m.sql.builder.query.returning.FilterOrReturn;

public interface UpdateOpts extends AdditionalFromTables<FilterOrReturn<UpdateQuery>>,
                                    FilterOrReturn<UpdateQuery> { }