package de.flojo.core.version.ht;

import de.flojo.core.version.IPackageMetaInformation;

public interface IRetrieveHtMeta {
	String DEFAULT_GROUP_ID = "de.flojo";

	default IPackageMetaInformation getMeta(String artifactId) {
		return getMeta(DEFAULT_GROUP_ID, artifactId);
	}

	IPackageMetaInformation getMeta(final String groupId, final String artifactId);
}
