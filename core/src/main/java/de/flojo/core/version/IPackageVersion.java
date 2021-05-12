package de.flojo.core.version;

import java.io.Serializable;

public interface IPackageVersion extends Serializable {
	String getVersionString();

	default int getMajor() {
		return 0;
	}

	default int getMinor() {
		return 0;
	}

	default int getPatch() {
		return 0;
	}
}
