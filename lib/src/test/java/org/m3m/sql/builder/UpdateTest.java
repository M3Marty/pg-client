package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.m3m.sql.builder.SqlFunctions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.query.where.WhereOps.*;
public class UpdateTest {

	@Test
	public void updateByFilterTest() {
		String query = update("films").set(field("kind"), "Dramatic")
				.where("kind", eq("Drama")).build();

		assertEquals("UPDATE films SET kind = 'Dramatic' WHERE kind = 'Drama'", query);
	}

	@Test
	public void updateAndResetTest() {
		String query = update("weather")
				.set(field("temp_lo"), raw("temp_lo+1"))
				.set(field("temp_hi"), raw("temp_lo+15"))
				.set(field("prcp"), defaultValue())
				.where("city", eq("San Francisco")).and("date", eq("2003-07-03"))
				.build();

		assertEquals("UPDATE weather SET temp_lo = temp_lo+1,temp_hi = temp_lo+15,prcp = DEFAULT WHERE city = 'San Francisco' AND date = '2003-07-03'", query);
	}

	@Test
	public void updateResetAndReturnTest() {
		String query = update("weather")
				.set(field("temp_lo"), raw("temp_lo+1"))
				.set(field("temp_hi"), raw("temp_lo+15"))
				.set(field("prcp"), defaultValue())
				.where("city", eq("San Francisco"))
				.and("date", eq("2003-07-03"))
				.returning(fields(field("temp_lo"), field("temp_hi"), field("prcp")));

		assertEquals("UPDATE weather SET temp_lo = temp_lo+1,temp_hi = temp_lo+15,prcp = DEFAULT WHERE city = 'San Francisco' AND date = '2003-07-03' RETURNING temp_lo,temp_hi,prcp", query);
	}

	@Test
	public void updateAndResetWithColumnListTest() {
		String query = update("weather")
				.set(fields(field("temp_lo"), field("temp_hi"), field("prcp")),
						values(raw("temp_lo+1"), raw("temp_lo+15"), defaultValue()))
				.where("city", eq("San Francisco")).and("date", eq("2003-07-03"))
				.build();

		assertEquals("UPDATE weather SET (temp_lo,temp_hi,prcp) = (temp_lo+1,temp_lo+15,DEFAULT) WHERE city = 'San Francisco' AND date = '2003-07-03'", query);
	}

	@Test
	public void updateWithFilterAndAdditionalSourceTest() {
		String query = update("employees")
				.set(field("sales_count"), raw("sales_count + 1"))
				.from(table("accounts"))
				.where("accounts.name", eq("Acme Corporation"))
				.and("employees.id", eq(field("accounts.sales_person")))
				.build();

		assertEquals("UPDATE employees SET sales_count = sales_count + 1 FROM accounts WHERE accounts.name = 'Acme Corporation' AND employees.id = accounts.sales_person", query);
	}

	@Test
	public void updateWithInnerQueryTest() {
		String query = update("employees").set(field("sales_count"), raw("sales_count + 1"))
				.where("id", eq(
						select("sales_person").from("accounts").where("name", eq("Acme Corporation")))
				).build();

		assertEquals("UPDATE employees SET sales_count = sales_count + 1 WHERE id = (SELECT sales_person FROM accounts WHERE name = 'Acme Corporation')", query);
	}

	@Test
	public void updateDataFromAnotherTableTest() {
		String query = update("accounts")
				.set(fields("contact_first_name", "contact_last_name"),
						select().values("first_name", "last_name").from("salesmen")
								.where("salesmen.id", eq(field("accounts.sales_id")))
				).build();

		assertEquals("UPDATE accounts SET (contact_first_name,contact_last_name) = (SELECT first_name,last_name FROM salesmen WHERE salesmen.id = accounts.sales_id)", query);
	}

	@Test
	public void updateDataFromAnotherTableUsingAdditionalSourceTest() {
		String query = update("accounts")
				.set(field("contact_first_name"), field("first_name"))
				.set(field("contact_last_name"), field("last_name"))
				.from(table("salesmen"))
				.where("salesmen.id", eq(field("accounts.sales_id")))
				.build();

		assertEquals("UPDATE accounts SET contact_first_name = first_name,contact_last_name = last_name FROM salesmen WHERE salesmen.id = accounts.sales_id", query);
	}

	@Test
	public void updateSummaryTableTest() {
		String query = update(table("summary").as("s"))
				.set(fields("sum_x", "sum_y", "avg_x", "avg_y"),
						select().values(sum("x"), sum("y"), avg("x"), avg("y"))
								.from(table("data").as("d"))
								.where("d.group_id", eq(field("s.group_id")))
				).build();

		assertEquals("UPDATE summary AS s SET (sum_x,sum_y,avg_x,avg_y) = (SELECT SUM(x),SUM(y),AVG(x),AVG(y) FROM data AS d WHERE d.group_id = s.group_id)", query);
	}

	@Test
	public void updateWhereCursorTest() {
		String query = update("films").set(field("kind"), "Dramatic")
				.whereCurrentOf("c_films").build();

		assertEquals("UPDATE films SET kind = 'Dramatic' WHERE CURRENT OF c_films", query);
	}
}
