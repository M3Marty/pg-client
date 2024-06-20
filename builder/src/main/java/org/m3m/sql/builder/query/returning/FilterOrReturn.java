package org.m3m.sql.builder.query.returning;

import org.m3m.sql.builder.query.where.*;

public interface FilterOrReturn
		extends WhereConditionOrCursor<ReturningConditionBuilder>,
		        ReturningConditionBuilder { }