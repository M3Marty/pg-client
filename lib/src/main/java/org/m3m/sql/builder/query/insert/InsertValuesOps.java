package org.m3m.sql.builder.query.insert;

import org.m3m.sql.builder.query.insert.conflict.AddOrOnConflictOrReturn;

public interface InsertValuesOps {

	AddOrOnConflictOrReturn setValuesExpression(String expression);

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