package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;
import org.m3m.sql.builder.query.insert.InsertQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.query.where.WhereOps.*;

public class SelectTest {

	@Test
	public void simpleDistinctSelectTest() {
		String query = select().distinct().values("column1", "column2")
				.from(table("table1").as("t1")).build();

		assertEquals("SELECT DISTINCT column1,column2 FROM table1 AS t1", query);
	}

	@Test
	public void simpleFilteredSelectTest() {
		String query = select().all().from(table("table"))
				.where("a", grThan(10)).build();

		assertEquals("SELECT * FROM table WHERE a > 10", query);
	}

	@Test
	public void simpleMultipleFiltersSelectTest() {
		String query = select().all().from(table("table"))
				.where("a", grThan(10)).or("b", lsThan(5)).build();

		assertEquals("SELECT * FROM table WHERE a > 10 OR b < 5", query);
	}
}