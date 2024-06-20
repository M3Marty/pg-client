package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.SqlFunctions.*;
import static org.m3m.sql.builder.query.where.WhereOps.*;

public class SelectTest {

	@Test
	public void simpleSelectTest() {
		String query = selectAll().from("table").build();

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
		String query = select().distinct().on("field").all().from("table").build();

		assertEquals("SELECT DISTINCT ON (field) * FROM table", query);
	}

	@Test
	public void simpleDistinctOnMultipleSelectTest() {
		String query = select().distinctOn("field", "another").all().from("table").build();

		assertEquals("SELECT DISTINCT ON (field,another) * FROM table", query);
	}


	@Test
	public void selectJoinTest() {
		String query = selectAll().from("table")
				.join(table("another").as("a")).on("field", eq(field("a.field")))
				.build();

		assertEquals("SELECT * FROM table JOIN another AS a ON field = a.field", query);
	}

	@Test
	public void selectNaturalJoinTest() {
		String query = selectAll().from("table").naturalLeftOuterJoin(table("another")).build();

		assertEquals("SELECT * FROM table NATURAL LEFT OUTER JOIN another", query);
	}

	@Test
	public void selectChainingJoinTest() {
		String query = selectAll().from("table")
				.fullOuterJoin(table("another")).on("table.id", eq(field("table_id")))
				.rightJoin(table("third")).on("another.id", eq(field("another_id")))
				.build();

		assertEquals("SELECT * FROM table FULL OUTER JOIN another ON table.id = table_id RIGHT JOIN third ON another.id = another_id", query);
	}

	@Test
	public void selectJoinUsingFieldsTest() {
		String query = selectAll().from("table")
				.innerJoin(table("another")).using("a", "b", "c").build();

		assertEquals("SELECT * FROM table INNER JOIN another USING (a,b,c)", query);
	}

	@Test
	public void selectJoiningSamplesTest() {
		String query = selectAll().from("table").sampleSystem(0.5)
				.naturalJoin(table("another")).build();

		assertEquals(
				"SELECT * FROM table TABLESAMPLE SYSTEM (0.5) NATURAL JOIN another",
				query);
	}

	@Test
	public void simpleFilteredSelectTest() {
		String query = selectAll().from("table").where("a", grThan(10)).build();

		assertEquals("SELECT * FROM table WHERE a > 10", query);
	}

	@Test
	public void simpleMultipleFiltersSelectTest() {
		String query = selectAll().from("table").where("a", grThan(10)).or("b", lsThan(5)).build();

		assertEquals("SELECT * FROM table WHERE a > 10 OR b < 5", query);
	}

	@Test
	public void simpleGroupSelectTest() {
		String query = selectAll().from("table").groupBy("field").build();

		assertEquals("SELECT * FROM table GROUP BY field", query);
	}

	@Test
	public void simpleGroupHavingSelectTest() {
		String query = selectAll().from("table")
				.groupBy("field").having(sum("field"), grThan(0)).build();

		assertEquals("SELECT * FROM table GROUP BY field HAVING SUM(field) > 0", query);
	}

	@Test
	public void simpleGroupMultipleHavingSelectTest() {
		String query = selectAll().from("table")
				.groupBy("field").having("sum(field)", grThan(0)).or("desc", eq(defaultValue()))
				.build();

		assertEquals("SELECT * FROM table GROUP BY field HAVING sum(field) > 0 OR desc = DEFAULT", query);
	}

	@Test
	public void simpleUnionSelectsTest() {
		String query = selectAll().from("table")
				.unionWith(select().all().from(table("another"))).build();

		assertEquals("SELECT * FROM table UNION SELECT * FROM another", query);
	}

	@Test
	public void simpleMultipleTableBiOperationsTest() {
		String query = selectAll().from("table")
				.intersectWith(select().all().from(table("another")))
				.except(select().all().from(table("third")))
				.build();

		assertEquals("SELECT * FROM table INTERSECT SELECT * FROM another EXCEPT SELECT * FROM third", query);
	}

	@Test
	public void simpleSortSelectTest() {
		String query = selectAll().from("table").orderBy("time_created").build();

		assertEquals("SELECT * FROM table ORDER BY time_created", query);
	}

	@Test
	public void simpleSortAndLimitTest() {
		String query = selectAll().from("table").orderBy("a", "b").limit(10).build();

		assertEquals("SELECT * FROM table ORDER BY a,b LIMIT 10", query);
	}

	@Test
	public void simpleSortAndFetchTest() {
		String query = selectAll().from("table").orderBy("time_created").fetch(20, 10).build();

		assertEquals("SELECT * FROM table ORDER BY time_created OFFSET 20 FETCH 10 ONLY", query);
	}

	@Test
	public void simpleSortAndOffsetTest() {
		String query = selectAll().from("table").orderBy("a", "b").offset(10).build();

		assertEquals("SELECT * FROM table ORDER BY a,b OFFSET 10", query);
	}

	@Test
	public void simpleBlockTest() {
		String query = selectAll().from("table").blockForUpdate().build();

		assertEquals("SELECT * FROM table FOR UPDATE", query);
	}

	@Test
	public void simpleBlockOfTableTest() {
		String query = selectAll().from("table").blockForShare().of("another").build();

		assertEquals("SELECT * FROM table FOR SHARE OF another", query);
	}

	@Test
	public void simpleBlockNoWaitTest() {
		String query = selectAll().from("table").blockForNoKeyUpdate().noWait().build();

		assertEquals("SELECT * FROM table FOR NO KEY UPDATE NOWAIT", query);
	}

	@Test
	public void simpleBlockSkipLockedTest() {
		String query = selectAll().from("table")
				.blockForKeyShare().of("another", "third").skipLocked().build();

		assertEquals("SELECT * FROM table FOR KEY SHARE OF another,third SKIP LOCKED", query);
	}

	@Test
	public void simpleNestedSelectInFromTest() {
		String query = selectAll().from(selectAll().from(table("inner_table")).as("subquery")).build();

		assertEquals("SELECT * FROM (SELECT * FROM inner_table) AS subquery", query);
	}

	@Test
	public void simpleTableSampleTest() {
		String query = selectAll().from("table1").sampleSystem(0.25).build();

		assertEquals("SELECT * FROM table1 TABLESAMPLE SYSTEM (0.25)", query);
	}

	@Test
	public void simpleFunctionSelectTest() {
		String query = selectAll().from(generateSeries(1, 10).as("series")).build();

		assertEquals("SELECT * FROM generate_series(1,10) AS series", query);
	}

	@Test
	public void simpleCombinedConditionsTest() {
		String query = select().values("t1.column1", "t2.column2")
				.from(table("table1").as("t1"))
				.join(table("table2").as("t2")).on("t1.id", eq(field("t2.id")))
				.where("t1.column1", eq("value1"))
				.groupBy("t1.column1").having(count("t2.column2"), grThan(5))
				.build();

		assertEquals("SELECT t1.column1,t2.column2 FROM table1 AS t1 JOIN table2 AS t2 ON t1.id = t2.id WHERE t1.column1 = 'value1' GROUP BY t1.column1 HAVING COUNT(t2.column2) > 5", query);
	}

	@Test
	public void selectDistinctValuesOrderedTest() {
		String query = select().distinctOn("location")
				.values("location", "time", "report")
				.from("weather_reports")
				.orderBy("location", desc("time"))
				.build();

		assertEquals("SELECT DISTINCT ON (location) location,time,report FROM weather_reports ORDER BY location,time DESC", query);
	}

	@Test
	public void selectValueOrderedTest() {
		String query = select("name").from("distributors")
				.orderBy("code").build();

		assertEquals("SELECT name FROM distributors ORDER BY code", query);
	}

	@Test
	public void selectAllFromBlockedQueryTest() {
		String query = selectAll()
				.from(selectAll().from("mytable").blockForUpdate().as("ss"))
				.where("col1", eq(5)).build();

		assertEquals("SELECT * FROM (SELECT * FROM mytable FOR UPDATE) AS ss WHERE col1 = 5", query);
	}

	@Test
	public void selectFromTwoTablesTest() {
		String query = select().values("f.title", "f.did", "d.name", "f.date_prod", "f.kind")
				.from(table("distributors").as("d"), table("films").as("f"))
				.where("f.did", eq(field("d.did"))).build();

		assertEquals("SELECT f.title,f.did,d.name,f.date_prod,f.kind FROM distributors AS d,films AS f WHERE f.did = d.did", query);
	}

	@Test
	public void selectValuesFromGroupsTest() {
		String query = select().values(field("kind"), sum("len").as("total"))
				.from("films").groupBy("kind").build();

		assertEquals("SELECT kind,SUM(len) AS total FROM films GROUP BY kind", query);
	}

	@Test
	public void filteringGroupsTest() {
		String query = select().values(field("kind"), sum("len").as("total")).from("films")
				.groupBy("kind").having(sum("len"), lsThan(hours(5))).build();

		assertEquals("SELECT kind,SUM(len) AS total FROM films GROUP BY kind HAVING SUM(len) < INTERVAL '5 hours'", query);
	}

	@Test
	public void selectOrderedTest() {
		String query = select().all().from("distributors").orderBy("name").build();

		assertEquals("SELECT * FROM distributors ORDER BY name", query);
	}

	@Test
	public void unionTablesTest() {
		String query = select().values("distributors.name").from("distributors")
				.where("distributors.name", like("W%"))
				.unionWith(
						select().values("actors.name").from("actors")
						.where("actors.name", like("W%"))
				).build();

		assertEquals("SELECT distributors.name FROM distributors WHERE distributors.name LIKE 'W%' UNION SELECT actors.name FROM actors WHERE actors.name LIKE 'W%'", query);
	}
}