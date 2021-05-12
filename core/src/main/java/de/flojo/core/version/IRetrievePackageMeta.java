package de.flojo.core.version;

import de.flojo.core.filters.IFilterInformation;
import de.flojo.core.filters.StandardFilters;

import java.util.Set;

public interface IRetrievePackageMeta {
	default Set<PackageMetaInformation> getPackages() {
		return getPackages(StandardFilters.filterTrue());
	}

	Set<PackageMetaInformation> getPackages(
			IFilterInformation<PackageMetaInformation, PackageMetaInformation> packageFilter);
}
