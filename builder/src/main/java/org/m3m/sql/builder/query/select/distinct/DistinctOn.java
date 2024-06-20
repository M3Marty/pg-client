package org.m3m.sql.builder.query.select.distinct;

import org.m3m.sql.builder.query.select.SelectValues;

public interface DistinctOn {

	SelectValues on(String...fields);
}