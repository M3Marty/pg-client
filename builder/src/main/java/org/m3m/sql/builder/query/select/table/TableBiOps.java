package org.m3m.sql.builder.query.select.table;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.order.SortableSelect;

public interface TableBiOps extends SortableSelect, Query {

	StringBuilder getTableExpression();

	default TableBiOps unionWith(String expression) {
		getTableExpression().append(" UNION ").append(expression);
		return this;
	}

	default TableBiOps intersectWith(String expression) {
		getTableExpression().append(" INTERSECT ").append(expression);
		return this;
	}

	default TableBiOps except(String expression) {
		getTableExpression().append(" EXCEPT ").append(expression);
		return this;
	}

	default TableBiOps unionWith(Query query) {
		return unionWith(query.build());
	}

	default TableBiOps intersectWith(Query query) {
		return intersectWith(query.build());
	}

	default TableBiOps except(Query query) {
		return except(query.build());
	}
}