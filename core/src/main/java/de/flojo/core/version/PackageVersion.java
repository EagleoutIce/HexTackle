package de.flojo.core.version;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public class PackageVersion implements Serializable {
	private final int major;
	private final int minor;
	private final int patch;

	public PackageVersion(final String versionString) {
		String[] base = versionString.split("\\.");
		this.major = toVersionPart(base, 0, 0);
		this.minor = toVersionPart(base, 1, 0);
		this.patch = toVersionPart(base, 2, 0);
	}

	public PackageVersion(final int major, final int minor, final int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	private static int toVersionPart(String[] arr, int idx, int defaultValue) {
		if (arr.length <= idx)
			return defaultValue;
		try {
			return Integer.parseInt(arr[idx]);
		} catch (NumberFormatException ignored) {
			return defaultValue;
		}
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
