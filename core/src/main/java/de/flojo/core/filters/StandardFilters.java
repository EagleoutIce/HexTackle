package de.flojo.core.filters;

import java.util.Objects;
import java.util.regex.Pattern;

public class StandardFilters {
	private StandardFilters() {
	}

	public static IFilterInformation<String, String> stringFilter(String match) {
		return data -> new DefaultFilterReport<>(FilterResult.fromBoolean(Objects.equals(match, data)), data);
	}

	public static IFilterInformation<String, String> regexFilter(Pattern pattern) {
		return data -> new DefaultFilterReport<>(FilterResult.fromBoolean(pattern.matcher(data).matches()), data);
	}

	public static <X> IFilterInformation<X, X> filterTrue() {
		return data -> new DefaultFilterReport<X>(FilterResult.DOES_MATCH, data);
	}

	public static <X> IFilterInformation<X, X> filterFalse() {
		return data -> new DefaultFilterReport<>(FilterResult.DOES_NOT_MATCH, data);
	}
}
