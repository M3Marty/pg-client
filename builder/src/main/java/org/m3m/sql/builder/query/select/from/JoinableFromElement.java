package org.m3m.sql.builder.query.select.from;

import org.m3m.sql.builder.query.from.TableDataSource;
import org.m3m.sql.builder.query.select.SelectOps;

public interface JoinableFromElement extends FromExpression, SelectOps {

	default JoiningCondition join(TableDataSource table) {
		return appendFromExpression("JOIN " + table.buildExpression());
	}

	default JoiningCondition innerJoin(TableDataSource table) {
		return appendFromExpression("INNER JOIN " + table.buildExpression());
	}

	default JoiningCondition rightJoin(TableDataSource table) {
		return appendFromExpression("RIGHT JOIN " + table.buildExpression());
	}

	default JoiningCondition leftJoin(TableDataSource table) {
		return appendFromExpression("LEFT JOIN " + table.buildExpression());
	}

	default JoiningCondition fullJoin(TableDataSource table) {
		return appendFromExpression("FULL JOIN " + table.buildExpression());
	}

	default JoiningCondition rightOuterJoin(TableDataSource table) {
		return appendFromExpression("RIGHT OUTER JOIN " + table.buildExpression());
	}

	default JoiningCondition leftOuterJoin(TableDataSource table) {
		return appendFromExpression("LEFT OUTER JOIN " + table.buildExpression());
	}

	default JoiningCondition fullOuterJoin(TableDataSource table) {
		return appendFromExpression("FULL OUTER JOIN " + table.buildExpression());
	}

	default JoinableFromElement crossJoin(TableDataSource table) {
		return appendFromExpression("CROSS JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalJoin(TableDataSource table) {
		return appendFromExpression("NATURAL JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalInnerJoin(TableDataSource table) {
		return appendFromExpression("NATURAL INNER JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalRightJoin(TableDataSource table) {
		return appendFromExpression("NATURAL RIGHT JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalLeftJoin(TableDataSource table) {
		return appendFromExpression("NATURAL LEFT JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalFullJoin(TableDataSource table) {
		return appendFromExpression("NATURAL FULL JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalRightOuterJoin(TableDataSource table) {
		return appendFromExpression("NATURAL RIGHT OUTER JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalLeftOuterJoin(TableDataSource table) {
		return appendFromExpression("NATURAL LEFT OUTER JOIN " + table.buildExpression());
	}

	default JoinableFromElement naturalFullOuterJoin(TableDataSource table) {
		return appendFromExpression("NATURAL FULL OUTER JOIN " + table.buildExpression());
	}
}