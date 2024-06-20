package org.m3m.sql.builder.query.insert;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.insert.conflict.AddOrOnConflictOrReturn;
import org.m3m.sql.builder.query.insert.conflict.OnConflictOrReturn;

public interface InsertValuesOps {

	AddOrOnConflictOrReturn setValuesExpression(String expression);

	OnConflictOrReturn from(Query select);

	default AddOrOnConflictOrReturn values() {
		return setValuesExpression("VALUES ");
	}

	default AddOrOnConflictOrReturn values(String...fields) {
		return setValuesExpression("(" + String.join(",", fields) + ") VALUES ");
	}

	default AddOrOnConflictOrReturn defaultValues() {
		return setValuesExpression("DEFAULT VALUES");
	}
}