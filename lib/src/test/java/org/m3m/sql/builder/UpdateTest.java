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
				.set(field("temp_hi"), raw("temp_lo+15"))
				.set(field("prcp"), defaultValue())
				.where("city", eq("San Francisco"))
				.and("date", eq("2003-07-03"))
				.build();

		assertEquals("UPDATE weather SET temp_lo = temp_lo+1,temp_hi = temp_lo+15,prcp = DEFAULT WHERE city = 'San Francisco' AND date = '2003-07-03'", query);
	}

	@Test
	public void updateResetAndReturnTest() {
		String query = update().in(table("weather"))
				.set(field("temp_lo"), raw("temp_lo+1"))
				.set(field("temp_hi"), raw("temp_lo+15"))
				.set(field("prcp"), defaultValue())
				.where("city", eq("San Francisco"))
				.and("date", eq("2003-07-03"))
				.then().returning(fields(field("temp_lo"), field("temp_hi"), field("prcp")));

		assertEquals("UPDATE weather SET temp_lo = temp_lo+1,temp_hi = temp_lo+15,prcp = DEFAULT WHERE city = 'San Francisco' AND date = '2003-07-03' RETURNING temp_lo,temp_hi,prcp", query);
	}

	@Test
	public void updateAndResetWithColumnListTest() {
		String query = update().in(table("weather"))
				.set(fields(field("temp_lo"), field("temp_hi"), field("prcp")),
						values(raw("temp_lo+1"), raw("temp_lo+15"), defaultValue()))
				.where("city", eq("San Francisco")).and("date", eq("2003-07-03"))
				.build();

		assertEquals("UPDATE weather SET (temp_lo,temp_hi,prcp) = (temp_lo+1,temp_lo+15,DEFAULT) WHERE city = 'San Francisco' AND date = '2003-07-03'", query);
	}

}