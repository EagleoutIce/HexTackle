package de.flojo.core.version;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PackageMetaInformation implements IPackageMetaInformation {
	private final String groupId;
	private final String artifactId;
	private final IPackageVersion version;

	public PackageMetaInformation(final String groupId, final String artifactId,
								  final IPackageVersion version) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public IPackageVersion getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "PackageMetaInformation{" +
				"groupId='" + groupId + '\'' +
				", artifactId='" + artifactId + '\'' +
				", version=" + version +
				'}';
	}
}
