package org.m3m.sql.builder.query.update;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.DataSource;
import org.m3m.sql.builder.query.returning.FilterOrReturn;
import org.m3m.sql.builder.query.where.WhereQuery;

import java.util.ArrayList;
import java.util.List;

public class UpdateSetBuilder implements Query, UpdateOpts, UpdateSetOpts<UpdateSetBuilder> {

	@Getter
	private UpdateQuery parent;

	private final List<String> setExpressions = new ArrayList<>();

	@Override
	public void setParent(Query query) {
		if (query instanceof UpdateQuery uq) {
			this.parent = uq;
		}

		throw new IllegalArgumentException("UpdateSetBuilder required UpdateQuery as parent");
	}

	public UpdateSetBuilder(UpdateQuery parent) {
		this.parent = parent;
	}

	@Override
	public String buildExpression() {
		return String.join(",", setExpressions);
	}

	@Override
	public void setReturningExpression(String expression) {
		parent.setReturningExpression(expression);
	}

	@Override
	public FilterOrReturn<UpdateQuery> from(DataSource... dataSource) {
		return parent.from(dataSource);
	}

	@Override
	public WhereQuery<UpdateQuery> setWhereQuery(WhereQuery<UpdateQuery> whereQuery) {
		return parent.setWhereQuery(whereQuery);
	}

	@Override
	public UpdateSetBuilder set(String field, String expression) {
		setExpressions.add(field + " = " + expression);
		return this;
	}
}