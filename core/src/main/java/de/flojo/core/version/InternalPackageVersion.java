package de.flojo.core.version;

import lombok.ToString;

@ToString
public class InternalPackageVersion implements IPackageVersion {
	@Override
	public String getVersionString() {
		return "<Internal>";
	}
}
