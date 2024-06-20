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

		assertEquals(
				"SELECT * FROM table TABLESAMPLE SYSTEM (0.5) NATURAL JOIN another",
				query);
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

	@Test
	public void simpleUnionSelectsTest() {
		String query = select().all().from(table("table"))
				.unionWith(select().all().from(table("another"))).build();

		assertEquals("SELECT * FROM table UNION SELECT * FROM another", query);
	}

	@Test
	public void simpleMultipleTableBiOperationsTest() {
		String query = select().all().from(table("table"))
				.intersectWith(select().all().from(table("another")))
				.except(select().all().from(table("third")))
				.build();

		assertEquals("SELECT * FROM table INTERSECT SELECT * FROM another EXCEPT SELECT * FROM third", query);
	}

	@Test
	public void simpleSortSelectTest() {
		String query = select().all().from(table("table")).orderBy("time_created").build();

		assertEquals("SELECT * FROM table ORDER BY time_created", query);
	}

	@Test
	public void simpleSortAndLimitTest() {
		String query = select().all().from(table("table")).orderBy("a", "b")
				.limit(10).build();

		assertEquals("SELECT * FROM table ORDER BY a,b LIMIT 10", query);
	}

	@Test
	public void simpleSortAndFetchTest() {
		String query = select().all().from(table("table")).orderBy("time_created")
				.fetch(20, 10).build();

		assertEquals("SELECT * FROM table ORDER BY time_created OFFSET 20 FETCH 10 ONLY", query);
	}

	@Test
	public void simpleSortAndOffsetTest() {
		String query = select().all().from(table("table")).orderBy("a", "b")
				.offset(10).build();

		assertEquals("SELECT * FROM table ORDER BY a,b OFFSET 10", query);
	}

	@Test
	public void simpleBlockTest() {
		String query = select().all().from(table("table")).blockForUpdate().build();

		assertEquals("SELECT * FROM table FOR UPDATE", query);
	}

	@Test
	public void simpleBlockOfTableTest() {
		String query = select().all().from(table("table")).blockForShare().of("another").build();

		assertEquals("SELECT * FROM table FOR SHARE OF another", query);
	}

	@Test
	public void simpleBlockNoWaitTest() {
		String query = select().all().from(table("table")).blockForNoKeyUpdate().noWait();

		assertEquals("SELECT * FROM table FOR NO KEY UPDATE NOWAIT", query);
	}

	@Test
	public void simpleBlockSkipLockedTest() {
		String query = select().all().from(table("table"))
				.blockForKeyShare().of("another", "third").skipLocked();

		assertEquals("SELECT * FROM table FOR KEY SHARE OF another,third SKIP LOCKED", query);
	}
}