package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {

	@Test
	public void selectAllTest() {
		assertEquals(
				"SELECT * FROM table",
				selectAll().from(table("table")).build()
		);
	}

	@Test
	public void selectFieldTest() {
		assertEquals(
				"SELECT field FROM table",
				select("field").from(table("table")).build()
		);
	}

	@Test
	public void selectFieldWithBuilderTest() {
		assertEquals(
				"SELECT field FROM table",
				select()
						.entry(field("field"))
						.from(table("table")).build()
		);
	}

	@Test
	public void selectFieldWithAliasWithBuilderTest() {
		assertEquals(
				"SELECT field AS alias_field FROM table",
				select()
						.entry(field("field", "alias_field"))
						.from(table("table")).build()
		);
	}

	@Test
	public void selectMultipleFieldsWithAliasesWithBuilderTest() {
		assertEquals(
				"SELECT field AS alias_field, another AS another_alias FROM table",
				select()
						.entry(field("field", "alias_field"))
						.entry(field("another", "another_alias"))
						.from(table("table")).build()
		);
	}
}