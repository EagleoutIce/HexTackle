package de.flojo.core.version;

import java.util.Optional;

public interface IRetrievePackageMeta {
	Optional<PackageMetaInformation> getPackage(String from);
}
