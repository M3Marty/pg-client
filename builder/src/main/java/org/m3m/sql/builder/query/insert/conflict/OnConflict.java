package org.m3m.sql.builder.query.insert.conflict;

import org.m3m.sql.builder.query.from.TableDataSource;
import org.m3m.sql.builder.query.insert.InsertQuery;
import org.m3m.sql.builder.query.returning.Returning;
import org.m3m.sql.builder.query.update.UpdateSetOps;

public interface OnConflict {

	StringBuilder getOnConflictExpression();

	default InsertQuery appendOnConflictExpression(String expression) {
		getOnConflictExpression().append(' ').append(expression);
		return (InsertQuery) this;
	}

	default Returning doNothing() {
		return appendOnConflictExpression("DO NOTHING");
	}

	default UpdateSetOps doUpdate() {
		return new OnConflictUpdateQuery(this)
				.from((TableDataSource) null);
	}
}