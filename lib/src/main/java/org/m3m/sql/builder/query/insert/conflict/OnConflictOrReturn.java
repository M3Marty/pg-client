package org.m3m.sql.builder.query.insert.conflict;

import org.m3m.sql.builder.query.returning.Returning;

public interface OnConflictOrReturn extends Returning {

	OnConflictBuilder onConflict();
}