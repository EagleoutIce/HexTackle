package de.flojo.core.version;

import de.flojo.core.filters.DefaultFilterReport;
import de.flojo.core.filters.FilterReport;
import de.flojo.core.filters.FilterResult;
import de.flojo.core.filters.IFilterInformation;

import java.util.Objects;

public class PackageMetaInformationFilter implements IFilterInformation<PackageMetaInformation, PackageMetaInformation> {

	final PackageMetaInformation fuzzy;

	public PackageMetaInformationFilter(final PackageMetaInformation fuzzy) {
		this.fuzzy = fuzzy;
	}

	// Regex support?
	@Override
	public FilterReport<PackageMetaInformation> filter(final PackageMetaInformation data) {
		// cheap, do all
		final var matchesGroup = fuzzyMatchSingle(fuzzy.getGroupId(), data.getGroupId());
		final var matchesArtifact = fuzzyMatchSingle(fuzzy.getArtifactId(), data.getArtifactId());
		final var matchesVersion = fuzzyMatchVersion(fuzzy.getVersion(), data.getVersion());
		return new DefaultFilterReport<>(FilterResult.fromBoolean(matchesGroup && matchesArtifact && matchesVersion),
										 data);
	}

	private boolean fuzzyMatchVersion(final PackageVersion fuzzy, final PackageVersion got) {
		if (fuzzy == null)
			return true;
		final var matchesMajor = fuzzyMatchSingle(fuzzy.getMajor(), got.getMajor());
		final var matchesMinor = fuzzyMatchSingle(fuzzy.getMinor(), got.getMinor());
		final var matchesPatch = fuzzyMatchSingle(fuzzy.getPatch(), got.getPatch());
		return matchesMajor && matchesMinor && matchesPatch;
	}

	private <X> boolean fuzzyMatchSingle(final X fuzzyData, final X got) {
		return fuzzyData == null || Objects.equals(fuzzyData, got);
	}
}
