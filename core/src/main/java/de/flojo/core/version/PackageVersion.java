package de.flojo.core.version;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class PackageVersion {
	private final int major;
	private final int minor;
	private final int patch;

	public PackageVersion(final int major, final int minor, final int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}
}
