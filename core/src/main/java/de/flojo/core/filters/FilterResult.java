package de.flojo.core.filters;

public enum FilterResult {
	DOES_MATCH,
	DOES_NOT_MATCH,
	ERROR;

	public static FilterResult fromBoolean(boolean base) {
		return base ? DOES_MATCH : DOES_NOT_MATCH;
	}
}
