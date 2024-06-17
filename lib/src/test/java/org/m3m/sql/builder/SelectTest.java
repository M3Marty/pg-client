package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.query.where.WhereOps.*;

public class SelectTest {

	@Test
	public void simpleSelectTest() {
		String query = select().distinct().values("column1", "column2")
				.from(table("table1").as("t1")).build();

		assertEquals("SELECT DISTINCT column1,column2 FROM table1 AS t1", query);
	}
}
