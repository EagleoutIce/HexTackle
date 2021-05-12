package de.flojo.core.version;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public class PackageMetaInformation implements Serializable {
	private final String groupId;
	private final String artifactId;
	private final PackageVersion version;

	public PackageMetaInformation(final String groupId, final String artifactId,
								  final PackageVersion version) {
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

	public PackageVersion getVersion() {
		return version;
	}
}
