package org.m3m.sql.builder.query.insert;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.Sql;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.SimpleFromAliasInto;
import org.m3m.sql.builder.query.from.TableDataSource;
import org.m3m.sql.builder.query.where.WhereQuery;

import java.util.*;
import java.util.stream.Collectors;

public class InsertQuery implements SimpleFromAliasInto<InsertValuesOps>,
                                    InsertValuesOps, InsertOps {

	private static final String CONFLICT_WHERE = "${CONFLICT_WHERE}";

	@Setter @Getter
	private Query parent;

	private TableDataSource dataSource;

	private String valuesExpression;

	@Setter
	private String returningExpression;

	private StringBuilder onConflictBuilder;

	private final List<String> values = new ArrayList<>();

	private WhereQuery<OnConflict> whereQuery;

	@Override
	public String build() {
		if (dataSource == null) {
			throw new IllegalStateException("Can't insert without target");
		}

		if (valuesExpression == null) {
			throw new IllegalStateException("Nothing to insert");
		}

		StringBuilder builder = new StringBuilder("INSERT INTO ")
				.append(dataSource.buildExpression()).append(' ')
				.append(valuesExpression)
				.append(String.join(",", values));

		if (onConflictBuilder != null && onConflictBuilder.length() > 11) {
			if (whereQuery != null) {
				builder.append(' ').append(onConflictBuilder.toString()
						.replace(CONFLICT_WHERE, "WHERE " + whereQuery.buildExpression()));
			}
			else {
				builder.append(' ').append(onConflictBuilder);
			}
		}

		if (returningExpression != null) {
			builder.append(' ').append(returningExpression);
		}

		return builder.toString();
	}

	@Override
	public InsertValuesOps from(TableDataSource dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	@Override
	public AddOrOnConflictOrReturn setValuesExpression(String expression) {
		this.valuesExpression = expression;
		return this;
	}

	@Override
	public String buildExpression() {
		return build();
	}

	@Override
	public AddOrOnConflictOrReturn add(Object...values) {
		Iterable<String> valuesIterable = Arrays.stream(values)
				.map(Sql::getObjectStringValue).collect(Collectors.toList());
		this.values.add("(" + String.join(",", valuesIterable) + ")");

		return this;
	}

	@Override
	public InsertQuery appendOnConflictExpression(String expression) {
		if (onConflictBuilder == null) {
			onConflictBuilder = new StringBuilder("ON CONFLICT");
		}
		if (!expression.isEmpty()) {
			onConflictBuilder.append(' ').append(expression);
		}

		return this;
	}

	@Override
	public WhereQuery<OnConflict> setWhereQuery(WhereQuery<OnConflict> whereQuery) {
		appendOnConflictExpression(CONFLICT_WHERE);
		this.whereQuery = whereQuery;
		return whereQuery;
	}
}