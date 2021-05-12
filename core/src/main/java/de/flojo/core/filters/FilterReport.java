package de.flojo.core.filters;

public interface FilterReport<R> {
	FilterResult getResult();

	default boolean hasPackedInformation() {
		return false;
	}

	default R getPackedInformation() {
		throw new UnsupportedOperationException("No information packed");
	}
}
