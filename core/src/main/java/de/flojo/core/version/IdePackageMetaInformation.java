package de.flojo.core.version;

import lombok.ToString;

@ToString
public class IdePackageMetaInformation implements IPackageMetaInformation {
	private final String groupId;
	private final String artifactId;
	private final IPackageVersion version;

	public IdePackageMetaInformation(final String groupId, final String artifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = new IdePackageVersion();
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
