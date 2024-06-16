package org.m3m.sql.builder.query.insert;

public interface InsertValuesOpts {

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