package org.m3m.sql.builder.query.select.from;

public interface JoiningCondition extends FromExpression {

	default JoiningConditionBuilder on(String expression) {
		return appendFromExpression("ON " + expression);
	}

	default JoiningConditionBuilder on(String a, String b) {
		return appendFromExpression("ON " + a + b);
	}

	default JoinableFromElement using(String...fields) {
		return appendFromExpression("USING (" + String.join(",", fields) + ")");
	}
}