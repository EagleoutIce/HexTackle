package de.flojo.core.version;

import java.util.Optional;

public interface IRetrievePackageMeta {
	Optional<IPackageMetaInformation> getPackage(String from);
}
