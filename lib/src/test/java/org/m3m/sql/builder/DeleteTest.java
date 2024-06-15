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
				.where("producer_id", eq(field("producers.id")))
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

	@Test
	public void complexDeleteTest() {
		String query = delete()
				.from(table("orders").as("o"))
				.using(table("customers").as("c"))
				.where("o.customer_id", eq(field("c.id")))
				.and("c.country", eq("USA"))
				.and("o.order_date", lessThan(raw("NOW() - INTERVAL '1 year'")))
				.and("o.quantity", lessThan(5))
				.then().returning(all());

		assertEquals("DELETE FROM orders o USING customers c WHERE o.customer_id = c.id AND c.country = 'USA' AND o.order_date < NOW() - INTERVAL '1 year' AND o.quantity < 5 RETURNING *", query);
	}

	/**
	 * TODO
	 *
	 * DELETE FROM orders o
	 * USING customers c
	 * WHERE o.customer_id = c.id
	 * AND c.country = 'USA'
	 * AND o.id IN (
	 *     SELECT o2.id
	 *     FROM orders o2
	 *     WHERE o2.order_date < NOW() - INTERVAL '1 year'
	 *     AND o2.quantity < (
	 *         SELECT AVG(o3.quantity)
	 *         FROM orders o3
	 *         WHERE o3.customer_id = o2.customer_id
	 *     )
	 * )
	 * RETURNING o.id, o.customer_id, o.product_id, o.quantity, o.order_date;
	 */
//	@Test
//	public void complexDeleteWithSelectTest() {
//		String query = delete()
//				.from(table("orders").as("o"))
//				.using(table("customer").as("c"))
//	}
}