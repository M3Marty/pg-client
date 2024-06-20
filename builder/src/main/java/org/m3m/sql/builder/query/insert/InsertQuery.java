package org.m3m.sql.builder.query.insert;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.Sql;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.SimpleFromAliasInto;
import org.m3m.sql.builder.query.from.TableDataSource;
import org.m3m.sql.builder.query.insert.conflict.*;

import java.util.*;
import java.util.stream.Collectors;

public class InsertQuery implements SimpleFromAliasInto<InsertValuesOps>,
                                    ConflictConditionBuilder,
                                    InsertValuesOps, InsertOps {

	private TableDataSource dataSource;

	private String valuesExpression;

	@Setter
	private String returningExpression;

	@Getter
	private final StringBuilder onConflictExpression = new StringBuilder();

	private final List<String> values = new ArrayList<>();

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

		if (!getOnConflictExpression().isEmpty()) {
			builder.append(' ').append("ON CONFLICT");
			if (!getWhereExpression().isEmpty()) {
				builder.append(getOnConflictExpression());
			}
			else {
				builder.append(getOnConflictExpression());
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
	public OnConflictOrReturn from(Query select) {
		this.values.add(select.build());
		this.valuesExpression = "";
		return this;
	}

	@Override
	public StringBuilder getWhereExpression() {
		return getOnConflictExpression();
	}
}