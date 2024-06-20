package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.*;
import org.m3m.sql.builder.query.delete.DeleteOps;
import org.m3m.sql.builder.query.delete.DeleteQuery;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.insert.InsertQuery;
import org.m3m.sql.builder.query.insert.InsertValuesOps;
import org.m3m.sql.builder.query.select.distinct.DistinctOrSelectValues;
import org.m3m.sql.builder.query.select.SelectQuery;
import org.m3m.sql.builder.query.select.from.FromBuilder;
import org.m3m.sql.builder.query.update.*;

import java.util.Arrays;

@UtilityClass
public class Sql {

	ValueQuery DEFAULT = raw("DEFAULT");
	ValueQuery ALL = raw("*");

	ValueQuery defaultValue() {
		return DEFAULT;
	}
	ValueQuery all() {
		return ALL;
	}

	public String getObjectStringValue(Object value) {
		return switch (value) {
			case String str -> String.format("'%s'", str);
			case Number num -> num.toString();
			case Query query -> query.buildExpression();
			default -> throw new IllegalArgumentException("Unsupported type: "
					+ value.getClass().getSimpleName());
		};
	}

	public SimpleFromAliasInto<InsertValuesOps> insert() {
		return new InsertQuery();
	}

	public DistinctOrSelectValues select() {
		return new SelectQuery();
	}

	public FromBuilder select(String field) {
		return new SelectQuery().values(field);
	}

	public FromBuilder select(ValueQueryAs field) {
		return new SelectQuery().values(field);
	}

	public FromBuilder selectAll() {
		return new SelectQuery().all();
	}

	public SimpleFromAliasIn<UpdateSetOps> update() {
		return new UpdateQuery();
	}

	public UpdateSetOps update(String tableName) {
		return new UpdateQuery().in(tableName);
	}

	public UpdateSetOps update(TableDataSource table) {
		return new UpdateQuery().in(table);
	}

	public SimpleFrom<DeleteOps> delete() {
		return new DeleteQuery();
	}

	public TableDataSource table(String table) {
		return new TableDataSource(table);
	}

	public ValueQuery raw(String raw) {
		return new ValueQuery(raw);
	}

	public StringQuery str(Object value) {
		return new StringQuery(value);
	}

	public ValueQueryAs field(String field) {
		return new ValueQueryAs(field);
	}

	public ValueQuery excluded(String field) {
		return new ValueQuery("EXCLUDED." + field);
	}

	public ListQuery fields(ValueQueryAs...fields) {
		return new ListQuery((Object[]) fields);
	}

	public ListQuery fields(String...fields) {
		return new ListQuery(Arrays.stream(fields).map(Sql::field).toArray());
	}

	public ListQuery values(Query...values) {
		return new ListQuery((Object[]) values);
	}

	public String desc(String expression) {
		return expression + " DESC";
	}

	public String asc(String expression) {
		return expression + " ASC";
	}
}