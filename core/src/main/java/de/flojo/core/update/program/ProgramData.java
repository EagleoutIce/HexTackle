package de.flojo.core.update.program;

import lombok.ToString;

@ToString
public class ProgramData {
    private final String baseName;
    private final ProgramVersion version;

    public ProgramData(final String baseName, final ProgramVersion version) {
        this.baseName = baseName;
        this.version = version;
    }

    public String getBaseName() {
        return baseName;
    }

    public ProgramVersion getVersion() {
        return version;
    }

    public static final ProgramData INVALID = new ProgramData(null, null);
}
