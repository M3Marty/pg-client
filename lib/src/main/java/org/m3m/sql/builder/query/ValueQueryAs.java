package org.m3m.sql.builder.query;

public class ValueQueryAs extends ValueQuery implements As<ValueQueryAs> {

	private boolean isAssociated = false;

	@Override
	public ValueQueryAs as(String value) {
		if (this.isAssociated) {
			throw new IllegalStateException("Value already associated: " + value);
		}

		this.isAssociated = true;
		this.value += " AS " + value;
		return this;
	}

	public ValueQueryAs(String value) {
		super(value);
	}
}
