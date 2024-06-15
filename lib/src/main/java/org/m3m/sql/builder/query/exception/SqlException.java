package org.m3m.sql.builder.query.exception;

public class SqlException extends RuntimeException {

	public SqlException(String message) {
		super(message);
	}

	public SqlException(String message, Throwable cause) {
		super(message, cause);
	}

	public SqlException(Throwable cause) {
		super(cause);
	}
}