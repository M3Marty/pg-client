package org.m3m.sql.builder.query.insert.conflict;

import org.m3m.sql.builder.query.where.WhereCondition;

public interface FilterableOnConflict
		extends OnConflict, WhereCondition<ConflictConditionBuilder> { }