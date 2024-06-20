package org.m3m.sql.builder.query.select;

import org.m3m.sql.builder.query.*;
import org.m3m.sql.builder.query.select.from.FromBuilder;

public interface SelectValues {

	FromBuilder setSelectValuesExpression(String expression);

	default FromBuilder all() {
		return setSelectValuesExpression("*");
	}

	default FromBuilder values(ValueQuery...toSelect) {
		return values((Object[]) toSelect);
	}

	default FromBuilder values(String...toSelect) {
		return setSelectValuesExpression(String.join(",", toSelect));
	}

	default FromBuilder values(Object...toSelect) {
		return values(new ListQuery(toSelect).buildExpression());
	}
}