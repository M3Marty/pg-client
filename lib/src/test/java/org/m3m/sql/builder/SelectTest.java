package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.query.where.WhereOps.*;

public class SelectTest {

	@Test
	public void test() {
		select().all().from(table("table")).join(table("another"))
				.on("a", eq("b")).or("a", grThan("b"));
	}
}
