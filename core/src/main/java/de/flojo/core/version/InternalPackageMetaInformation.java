package de.flojo.core.version;

import lombok.ToString;

@ToString
public class InternalPackageMetaInformation implements IPackageMetaInformation {
	private final String groupId;
	private final String artifactId;
	private final IPackageVersion version;

	public InternalPackageMetaInformation(final String groupId, final String artifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = new InternalPackageVersion();
	}

	@Override
	public IPackageVersion getVersion() {
		return version;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}
}
