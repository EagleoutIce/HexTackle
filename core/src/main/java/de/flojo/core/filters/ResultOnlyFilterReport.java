package de.flojo.core.filters;

public class ResultOnlyFilterReport implements FilterReport<Void> {

	private final FilterResult result;

	public ResultOnlyFilterReport(final FilterResult result) {
		this.result = result;
	}


	@Override
	public FilterResult getResult() {
		return result;
	}

	@Override
	public boolean hasPackedInformation() {
		return false;
	}

	@Override
	public Void getPackedInformation() {
		throw new UnsupportedOperationException("There is no packed information");
	}
}
