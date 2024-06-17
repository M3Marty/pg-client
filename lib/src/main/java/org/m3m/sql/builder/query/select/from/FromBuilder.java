package org.m3m.sql.builder.query.select.from;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.TableDataSource;

public interface FromBuilder extends FromExpression {

	default JoinableAndSamplableTable from(TableDataSource table) {
		return appendFromExpression(table.buildExpression());
	}

	default JoinableFromElement from(Query selectOrFunction) {
		return appendFromExpression(selectOrFunction.buildExpression());
	}

	default JoinableFromElement fromLateral(Query selectOrFunction) {
		return appendFromExpression("LATERAL " + selectOrFunction.buildExpression());
	}
}