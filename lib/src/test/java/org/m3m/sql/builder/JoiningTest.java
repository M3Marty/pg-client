package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.query.where.WhereOps.*;
public class JoiningTest {

	@Test
	public void selectJoinTest() {
		String query = select().all().from(table("table"))
				.join(table("another").as("a")).on("field", eq(field("a.field")))
				.build();

		assertEquals("SELECT * FROM table JOIN another AS a ON field = a.field", query);
	}

	@Test
	public void selectNaturalJoinTest() {
		String query = select().all().from(table("table"))
				.naturalLeftOuterJoin(table("another"))
				.build();

		assertEquals("SELECT * FROM table NATURAL LEFT OUTER JOIN another", query);
	}

	@Test
	public void selectChainingJoinTest() {
		String query = select().all().from(table("table"))
				.fullOuterJoin(table("another")).on("table.id", eq(field("table_id")))
				.rightJoin(table("third")).on("another.id", eq(field("another_id")))
				.build();

		assertEquals("SELECT * FROM table FULL OUTER JOIN another ON table.id = table_id RIGHT JOIN third ON another.id = another_id", query);
	}

	@Test
	public void selectJoinUsingFieldsTest() {
		String query = select().all().from(table("table"))
				.innerJoin(table("another")).using("a", "b", "c").build();

		assertEquals("SELECT * FROM table INNER JOIN another USING (a,b,c)", query);
	}

	@Test
	public void selectJoiningSamplesTest() {
		String query = select().all().from(table("table")).sampleSystem(0.5)
				.naturalJoin(table("another")).build();

		assertEquals("SELECT * FROM table TABLESAMPLE SYSTEM (0.5) NATURAL JOIN another", query);
	}
}