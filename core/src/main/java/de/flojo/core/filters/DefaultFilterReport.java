package de.flojo.core.filters;

public class DefaultFilterReport<T> implements FilterReport<T> {

	private final FilterResult result;
	private final T data;

	public DefaultFilterReport(final FilterResult result, final T data) {
		this.result = result;
		this.data = data;
	}

	@Override
	public FilterResult getResult() {
		return result;
	}

	@Override
	public boolean hasPackedInformation() {
		return true;
	}

	@Override
	public T getPackedInformation() {
		return data;
	}
}
