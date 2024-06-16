package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.query.returning.Returning.all;
import static org.m3m.sql.builder.query.where.WhereOps.*;
public class UpdateTest {

	@Test
	public void updateByFilterTest() {
		String query = update().in(table("films"))
				.set(field("kind"), "Dramatic")
				.where("kind", eq("Drama"))
				.then().build();

		assertEquals("UPDATE films SET kind = 'Dramatic' WHERE kind = 'Drama'", query);
	}

	@Test
	public void updateAndResetTest() {
		String query = update().in(table("weather"))
				.set(field("temp_lo"), raw("temp_lo+1"))
				.set(field("temp_hi"), raw("temp_hi+15"))
				.set(field("prcp"), defaultValue())
				.where("city", eq("San Francisco"))
				.and("date", eq("2003-07-03"))
				.then().build();

		assertEquals("UPDATE weather SET temp_lo = temp_lo+1,temp_hi = temp_lo+15,prcp = DEFAULT WHERE city = 'San Francisco' AND date = '2003-07-03'", query);
	}
}