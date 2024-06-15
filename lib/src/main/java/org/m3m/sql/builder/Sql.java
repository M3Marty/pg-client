package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.ValueQuery;
import org.m3m.sql.builder.query.ValueQueryAs;
import org.m3m.sql.builder.query.delete.DeleteOps;
import org.m3m.sql.builder.query.delete.DeleteQuery;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.update.UpdateQuery;
import org.m3m.sql.builder.query.update.UpdateSetBuilder;

@UtilityClass
public class Sql {

	public SimpleFrom<DeleteOps> delete() {
		return new DeleteQuery();
	}

	public SimpleFromAliasIn<UpdateSetBuilder> update() {
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