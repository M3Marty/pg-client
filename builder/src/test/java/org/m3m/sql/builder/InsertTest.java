package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.query.where.WhereOps.lsThan;
import static org.m3m.sql.builder.query.where.WhereOps.notEq;

public class InsertTest {

	@Test
	public void insertPlainValuesTest() {
		String query = insert().into("films").values()
				.add("UA502", "Bananas", 105, "1971-07-13", "Comedy", "82 minutes")
				.build();

		assertEquals("INSERT INTO films VALUES ('UA502','Bananas',105,'1971-07-13','Comedy','82 minutes')", query);
	}

	@Test
	public void insertSpecificValuesTest() {
		String query = insert().into("films")
				.values("code", "title", "did", "date_prod", "kind")
				.add("T_601", "Yojimbo", 106, "1961-06-16", "Drama")
				.build();

		assertEquals("INSERT INTO films (code,title,did,date_prod,kind) VALUES ('T_601','Yojimbo',106,'1961-06-16','Drama')", query);
	}

	@Test
	public void insertDefaultRowTest() {
		String query = insert().into("films").defaultValues().build();

		assertEquals("INSERT INTO films DEFAULT VALUES", query);
	}

	@Test
	public void insertMultipleRowsTest() {
		String query = insert().into("films")
				.values("code", "title", "did", "date_prod", "kind")
				.add("B6717", "Tampopo", 110, "1985-02-10", "Comedy")
				.add("HG120", "The Dinner Game", 140, defaultValue(), "Comedy")
				.build();

		assertEquals("INSERT INTO films (code,title,did,date_prod,kind) VALUES ('B6717','Tampopo',110,'1985-02-10','Comedy'),('HG120','The Dinner Game',140,DEFAULT,'Comedy')", query);
	}

	@Test
	public void insertValuesByInnerQueryTest() {
		String query = insert().into("films")
				.from(selectAll().from("tmp_films").where("date_prod", lsThan("2004-05-07")))
				.build();

		assertEquals("INSERT INTO films SELECT * FROM tmp_films WHERE date_prod < '2004-05-07'", query);
	}

	@Test
	public void insertAndReturnTest() {
		String query = insert().into("distributors").values("did", "dname")
				.add(defaultValue(), "XYZ Widgets").returning(field("did"));

		assertEquals("INSERT INTO distributors (did,dname) VALUES (DEFAULT,'XYZ Widgets') RETURNING did", query);
	}

	@Test
	public void insertWithConflictDoUpdateTest() {
		String query = insert().into("distributors").values("did", "dname")
				.add(5, "Gizmo Transglobal")
				.add(6, "Associated Computing Inc")
				.onConflict().fields("did")
				.doUpdate().set(field("dname"), excluded("dname"))
				.build();

		assertEquals("INSERT INTO distributors (did,dname) VALUES (5,'Gizmo Transglobal'),(6,'Associated Computing Inc') ON CONFLICT (did) DO UPDATE SET dname = EXCLUDED.dname", query);
	}

	@Test
	public void insertWithConflictDoNothingTest() {
		String query = insert().into("distributors").values("did", "dname")
				.add(5, "Gizmo Transglobal")
				.add(6, "Associated Computing Inc")
				.onConflict().fields("did")
				.doNothing().build();

		assertEquals("INSERT INTO distributors (did,dname) VALUES (5,'Gizmo Transglobal'),(6,'Associated Computing Inc') ON CONFLICT (did) DO NOTHING", query);
	}

	@Test
	public void insertWithFilteredUpdateConflictHandleTest() {
		String query = insert().into(table("distributors").as("d"))
				.values("did", "dname").add(8, "Anvil Distribution")
				.onConflict().fields("did")
				.doUpdate().set(field("dname"),
						str(excluded("dname")).cat(" (formerly ").cat(field("d.dname")).cat(")"))
				.where("d.zipcode", notEq("21201"))
				.build();

		assertEquals("INSERT INTO distributors AS d (did,dname) VALUES (8,'Anvil Distribution') ON CONFLICT (did) DO UPDATE SET dname = EXCLUDED.dname || ' (formerly ' || d.dname || ')' WHERE d.zipcode <> '21201'", query);
	}

	@Test
	public void insertWithConflictOnConstraintTest() {
		String query = insert().into("distributors")
				.values("did", "dname").add(9, "Antwerp Design")
				.onConflict().onConstraint("distributors_pkey").doNothing()
				.build();

		assertEquals("INSERT INTO distributors (did,dname) VALUES (9,'Antwerp Design') ON CONFLICT ON CONSTRAINT distributors_pkey DO NOTHING", query);
	}

	@Test
	public void insertWithConflictFilterTest() {
		String query = insert().into("distributors")
				.values("did", "dname").add(10, "Conrad International")
				.onConflict().fields("did").where("is_active").doNothing()
				.build();

		assertEquals("INSERT INTO distributors (did,dname) VALUES (10,'Conrad International') ON CONFLICT (did) WHERE is_active DO NOTHING", query);
	}
}