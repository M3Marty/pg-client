package org.m3m.sql.builder.query.delete;

import org.m3m.sql.builder.query.Then;
import org.m3m.sql.builder.query.returning.Returning;
import org.m3m.sql.builder.query.where.WhereOps;

public interface WhereOpsOrReturn extends Returning, WhereOps<WhereOpsOrReturn>,
                                          Then<Returning> {

}