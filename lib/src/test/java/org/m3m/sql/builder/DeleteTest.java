package org.m3m.sql.builder;

import org.junit.jupiter.api.Test;

import static org.m3m.sql.builder.Sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.m3m.sql.builder.query.returning.Returning.all;
import static org.m3m.sql.builder.query.where.WhereOps.*;

public class DeleteTest {

	@Test
	public void deleteUsingTest() {
		String query = delete()
				.from(table("films"))
				.using(table("producers"))
				.where("producer_id", eqField("producers.id"))
				.and("producers.name", eq("foo"))
				.then().build();

		assertEquals("DELETE FROM films USING producers " +
				"WHERE producer_id = producers.id AND producers.name = 'foo'", query);
	}

//	@Test
//	public void deleteWithInnerQueryTest() {
//		String query = delete()
//				.from(table("films"))
//				.where("producer_id", in(
//						select("id")
//								.from(table("producers"))
//								.where("name", eq("foo"))
//				)).build();
//	}

	@Test
	public void deleteNotEqFilterTest() {
		String query = delete()
				.from(table("films"))
				.where("kind", notEq("Musical"))
				.build();

		assertEquals("DELETE FROM films WHERE kind <> 'Musical'", query);
	}

	@Test
	public void deleteAllTest() {
		String query = delete().from(table("films")).build();
		assertEquals("DELETE FROM films", query);
	}

	@Test
	public void deleteWithFilterAndReturningTest() {
		String query = delete().from(table("tasks")).where("status", eq("DONE")).then().returning(all());
		assertEquals("DELETE FROM tasks WHERE status = 'DONE' RETURNING *", query);
	}

	@Test
	public void deleteWhereCursorTest() {
		String query = delete().from(table("tasks")).whereCurrentOf("c_tasks").build();
		assertEquals("DELETE FROM tasks WHERE CURRENT OF c_tasks", query);
	}
}