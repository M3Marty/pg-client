package org.m3m.sql.builder.query.from;

import lombok.*;
import org.m3m.sql.builder.query.Query;

@RequiredArgsConstructor
public class FromTableRequest implements FromQuery {

	@Getter @Setter
	private Query parent;

	private final String table;

	@Override
	public String buildFromQuery() {
		return table;
	}
}