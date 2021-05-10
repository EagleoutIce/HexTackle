package de.flojo.core.update.program;

import lombok.ToString;

@ToString
public class ProgramData {
	public static final ProgramData INVALID = new ProgramData(null, null);
	private final String baseName;
	private final ProgramVersion version;

	public ProgramData(final String baseName, final ProgramVersion version) {
		this.baseName = baseName;
		this.version = version;
	}

	public static boolean isValid(final ProgramData data) {
		return data != null && data.baseName != null && data.version != null;
	}

	public String getBaseName() {
		return baseName;
	}

	public ProgramVersion getVersion() {
		return version;
	}
}
