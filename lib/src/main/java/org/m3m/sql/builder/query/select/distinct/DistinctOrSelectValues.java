package org.m3m.sql.builder.query.select.distinct;

import org.m3m.sql.builder.query.select.SelectValues;

public interface DistinctOrSelectValues extends DistinctableSelect,
                                                SelectValues {

	default SelectValues distinctOn(String...fields) {
		return distinct().on(fields);
	}
}