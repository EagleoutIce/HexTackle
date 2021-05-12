package de.flojo.core.version;

import lombok.ToString;

@ToString
public class IdePackageVersion implements IPackageVersion {
	@Override
	public String getVersionString() {
		return "<Internal>";
	}
}
