package org.m3m.sql.builder.query.select.from;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.TableDataSource;

import java.util.Arrays;

public interface FromBuilder extends FromExpression {

	default JoinableAndSamplableTable from(String...tableNames) {
		return appendFromExpression(String.join(",", tableNames));
	}

	default JoinableAndSamplableTable from(TableDataSource table) {
		return appendFromExpression(table.buildExpression());
	}

	default JoinableAndSamplableTable from(TableDataSource...tables) {
		return appendFromExpression(String.join(",",
				Arrays.stream(tables)
						.map(Query::buildExpression)
						.toArray(String[]::new)
		));
	}


	default JoinableFromElement from(Query selectOrFunction) {
		return appendFromExpression(selectOrFunction.buildExpression());
	}

	default JoinableFromElement fromLateral(Query selectOrFunction) {
		return appendFromExpression("LATERAL " + selectOrFunction.buildExpression());
	}
}