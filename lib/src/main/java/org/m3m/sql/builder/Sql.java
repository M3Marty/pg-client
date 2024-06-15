package org.m3m.sql.builder;

import lombok.experimental.UtilityClass;
import org.m3m.sql.builder.query.delete.DeleteOps;
import org.m3m.sql.builder.query.delete.DeleteQuery;
import org.m3m.sql.builder.query.from.*;

@UtilityClass
public class Sql {

	public SimpleFrom<DeleteOps> delete() {
		return new DeleteQuery();
	}

	public String field(String field) {
		return field;
	}

	public String field(String field, String alias) {
		return field + " AS " + alias;
	}

	public DataSource table(String table) {
		return new TableDataSource(table);
	}
}