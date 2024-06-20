package org.m3m.sql.builder.query.select.from;

public interface JoinableAndSamplableTable extends JoinableFromElement,
                                                   SamplableTable,
                                                   FromExpression {

	@Override
	default JoinableFromElement sampleTable(String function, String...args) {
		return appendFromExpression(String.format("TABLESAMPLE %s (%s)",
				function, String.join(",", args)));
	}

	@Override
	default JoinableFromElement sampleTable(String function, double seed, String...args) {
		return appendFromExpression(String.format("TABLESAMPLE %s (%s) REPEATABLE %f",
				function, String.join(",", args), seed));
	}
}