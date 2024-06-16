package org.m3m.sql.builder.query.select.from;

public interface SamplableTable {

	JoinableFromElement sampleTable(String function, double seed, String...args);
	JoinableFromElement sampleTable(String function, String...args);

	default JoinableFromElement sampleBernoulli(double percent, double seed) {
		return sampleTable("BERNOULLI", seed, Double.toString(percent));
	}

	default JoinableFromElement sampleBernoulli(double percent) {
		return sampleTable("BERNOULLI", Double.toString(percent));
	}

	default JoinableFromElement sampleSystem(double percent, double seed) {
		return sampleTable("SYSTEM", seed, Double.toString(percent));
	}

	default JoinableFromElement sampleSystem(double percent) {
		return sampleTable("SYSTEM", Double.toString(percent));
	}
}