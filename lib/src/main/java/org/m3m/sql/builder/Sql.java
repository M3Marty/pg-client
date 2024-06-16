package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.*;
import org.m3m.sql.builder.query.delete.DeleteOps;
import org.m3m.sql.builder.query.delete.DeleteQuery;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.update.*;

@UtilityClass
public class Sql {

	ValueQuery DEFAULT = new ValueQuery("DEFAULT");

	ValueQuery defaultValue() {
		return DEFAULT;
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

	public SimpleFrom<DeleteOps> delete() {
		return new DeleteQuery();
	}

	public SimpleFromAliasIn<UpdateSetOpts<UpdateSetBuilder>> update() {
		return new UpdateQuery();
	}

	public TableDataSource table(String table) {
		return new TableDataSource(table);
	}

	public ValueQuery raw(String raw) {
		return new ValueQuery(raw);
	}

	public ValueQueryAs field(String field) {
		return new ValueQueryAs(field);
	}
}