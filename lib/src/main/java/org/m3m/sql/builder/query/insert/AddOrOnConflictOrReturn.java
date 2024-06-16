package org.m3m.sql.builder.query.insert;

import org.m3m.sql.builder.query.returning.Returning;

public interface AddOrOnConflictOrReturn extends Returning {

	AddOrOnConflictOrReturn add(Object...values);

	OnConflictBuilder onConflict();
}