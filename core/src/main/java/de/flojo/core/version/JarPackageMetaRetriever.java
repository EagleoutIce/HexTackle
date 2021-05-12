package de.flojo.core.version;

import de.flojo.core.filters.IFilterInformation;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class JarPackageMetaRetriever implements IRetrievePackageMeta {

	@Override
	public Set<PackageMetaInformation> getPackages(
			final IFilterInformation<PackageMetaInformation, PackageMetaInformation> packageFilter) {
		return null;
	}
}
