package org.m3m.sql.builder.query.select;

import org.m3m.sql.builder.query.InAlias;
import org.m3m.sql.builder.query.exception.NoSuchAggregateFunctionException;
import org.m3m.sql.builder.query.validation.AggregateFunction;
import org.m3m.sql.builder.query.validation.AggregateFunctionType;

import java.lang.reflect.Field;

public class AggregateQuery extends SelectQuery implements InAlias {

	/**
	 * Aggregates values as array or multidimensional array
	 */
	@AggregateFunction
	public static final String ARRAY_AGG = "ARRAY_AGG";
	/**
	 * Aggregates average value
	 */
	@AggregateFunction
	public static final String AVG = "AVG";
	@AggregateFunction
	public static final String BIT_AND = "BIT_AND";
	@AggregateFunction
	public static final String BIT_OR = "BIT_OR";
	@AggregateFunction
	public static final String BOOL_AND = "BOOL_AND";
	@AggregateFunction
	public static final String BOOL_OR = "BOOL_OR";
	@AggregateFunction
	public static final String COUNT = "COUNT";
	/**
	 * Logical and
	 */
	public static final String EVERY = BOOL_AND;
	/**
	 * Aggregates values as json array ignoring null values
	 */
	@AggregateFunction
	public static final String JSON_AGG = "JSON_AGG";
	/**
	 * Aggregates values as json array including null values
	 */
	@AggregateFunction
	public static final String JSONB_AGG = "JSONB_AGG";
	/**
	 * Aggregates values as key value json object
	 */
	@AggregateFunction(AggregateFunctionType.PAIR)
	public static final String JSON_OBJECT_AGG = "JSON_OBJECT_AGG";
	@AggregateFunction
	public static final String MAX = "MAX";
	@AggregateFunction
	public static final String MIN = "MIN";
	/**
	 * Concatenates values and separate them with delimiter
	 */
	@AggregateFunction(AggregateFunctionType.PAIR)
	public static final String STRING_AGG = "STRING_AGG";
	@AggregateFunction
	public static final String SUM = "SUM";
	/**
	 * Concatenates non-null xml values
	 * @see <a href="https://www.postgresql.org/docs/9.5/functions-xml.html#FUNCTIONS-XML-XMLAGG">Section 9.14.1.7</a>
	 */
	@AggregateFunction
	public static final String XMLAGG = "XMLAGG";

	private static Field validateFunction(String function, String...argv) {
		try {
			var f = AggregateQuery.class.getDeclaredField(function.toUpperCase());
			if (f.isAnnotationPresent(AggregateFunction.class))
				throw new NoSuchAggregateFunctionException(function, argv);

			return f;
		} catch (NoSuchFieldException e) {
			throw new NoSuchAggregateFunctionException(function, argv);
		}
	}

	public static String buildFunction(String function, String...argv) {
		Field f = validateFunction(function, argv);

		var declaration = f.getAnnotation(AggregateFunction.class);
		if (declaration.value().getArgsNumber() != argv.length) {
			throw new NoSuchAggregateFunctionException(function, declaration, argv);
		}

		return String.format("%s%s",  function,
				declaration.value().getFormat().formatted((Object[]) argv));
	}

	public AggregateQuery(String function, String...argv) {
		super(buildFunction(function, argv));
	}
}