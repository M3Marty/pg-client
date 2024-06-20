package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.query.where.WhereOps.*;

public class SelectTest {

	@Test
	public void simpleSelectTest() {
		String query = select().all().from(table("table")).build();

		assertEquals("SELECT * FROM table", query);
	}

	@Test
	public void simpleDistinctSelectTest() {
		String query = select().distinct().values("column1", "column2")
				.from(table("table1").as("t1")).build();

		assertEquals("SELECT DISTINCT column1,column2 FROM table1 AS t1", query);
	}

	@Test
	public void simpleDistinctOnSelectTest() {
		String query = select().distinct().on("field").all().from(table("table")).build();

		assertEquals("SELECT DISTINCT ON (field) * FROM table", query);
	}

	@Test
	public void simpleDistinctOnMultipleSelectTest() {
		String query = select().distinctOn("field", "another")
				.all().from(table("table")).build();

		assertEquals("SELECT DISTINCT ON (field,another) * FROM table", query);
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

	@Test
	public void simpleGroupSelectTest() {
		String query = select().all().from(table("table"))
				.groupBy("field").build();

		assertEquals("SELECT * FROM table GROUP BY field", query);
	}

	@Test
	public void simpleGroupHavingSelectTest() {
		String query = select().all().from(table("table"))
				.groupBy("field").having("sum(field)", grThan(0)).build();

		assertEquals("SELECT * FROM table GROUP BY field HAVING sum(field) > 0", query);
	}

	@Test
	public void simpleGroupMultipleHavingSelectTest() {
		String query = select().all().from(table("table"))
				.groupBy("field")
				.having("sum(field)", grThan(0)).or("desc", eq(defaultValue()))
				.build();

		assertEquals("SELECT * FROM table GROUP BY field HAVING sum(field) > 0 OR desc = DEFAULT", query);
	}
}