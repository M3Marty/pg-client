package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class InsertTest {

	@Test
	public void insertPlainValuesTest() {
		String query = insert().into(table("films")).values()
				.add("UA502", "Bananas", 105, "1971-07-13", "Comedy", "82 minutes")
				.build();

		assertEquals("INSERT INTO films VALUES ('UA502','Bananas',105,'1971-07-13','Comedy','82 minutes')", query);
	}

	@Test
	public void insertSpecificValuesTest() {
		String query = insert().into(table("films"))
				.values("code", "title", "did", "date_prod", "kind")
				.add("T_601", "Yojimbo", 106, "1961-06-16", "Drama")
				.build();

		assertEquals("INSERT INTO films (code,title,did,date_prod,kind) VALUES ('T_601','Yojimbo',106,'1961-06-16','Drama')", query);
	}

	@Test
	public void insertDefaultRowTest() {
		String query = insert().into(table("films")).defaultValues().build();

		assertEquals("INSERT INTO films DEFAULT VALUES", query);
	}

	@Test
	public void insertMultipleRowsTest() {
		String query = insert().into(table("films"))
				.values("code", "title", "did", "date_prod", "kind")
				.add("B6717", "Tampopo", 110, "1985-02-10", "Comedy")
				.add("HG120", "The Dinner Game", 140, defaultValue(), "Comedy")
				.build();

		assertEquals("INSERT INTO films (code,title,did,date_prod,kind) VALUES ('B6717','Tampopo',110,'1985-02-10','Comedy'),('HG120','The Dinner Game',140,DEFAULT,'Comedy')", query);
	}

	/**
	 * TODO
	 *
	 * INSERT INTO films SELECT * FROM tmp_films WHERE date_prod < '2004-05-07'
	 */
	@Test
	public void insertValuesByInnerQueryTest() {

	}

	@Test
	public void insertAndReturnTest() {
		String query = insert().into(table("distributors")).values("did", "dname")
				.add(defaultValue(), "XYZ Widgets").returning(field("did"));

		assertEquals("INSERT INTO distributors (did,dname) VALUES (DEFAULT,'XYZ Widgets') RETURNING did", query);
	}
}