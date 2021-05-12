package de.flojo.core.version;

import java.io.Serializable;

public interface IPackageMetaInformation extends Serializable {
	IPackageVersion getVersion();

	String getArtifactId();

	String getGroupId();
}
