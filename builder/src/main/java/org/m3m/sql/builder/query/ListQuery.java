package org.m3m.sql.builder.query;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.Sql;

public class ListQuery implements Query {

	private Object[] list;

	public ListQuery(Object...list) {
		this.list = list;
	}

	@Override
	public String buildExpression() {
		if (list == null || list.length == 0) {
			return "";
		}

		if (list.length == 1) {
			return Sql.getObjectStringValue(list[0]);
		}

		StringBuilder builder = new StringBuilder(Sql.getObjectStringValue(list[0]));
		for (int i = 1; i < list.length; i++) {
			builder.append(',').append(Sql.getObjectStringValue(list[i]));
		}
		return builder.toString();
	}
}
