package de.flojo.core.update.program;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
public class ProgramVersion {
    private final int major;
    private final int minor;
    private final int patch;


    private static int tryConvert(String tag, String base) throws ProgramVersionIdentificationException {
        try {
            return Integer.parseInt(base);
        } catch (NumberFormatException numberFormatException) {
            log.error("Identification of program version failed for {} and base {}.", tag, base);
            log.error("Supplied with error message: ", numberFormatException);
            throw new ProgramVersionIdentificationException("For tag " + tag + " and base " + base);
        }
    }

    public ProgramVersion(final String major, final String minor, final String patch) throws ProgramVersionIdentificationException {
        this(tryConvert("major", major), tryConvert("minor", minor), tryConvert("patch", patch));
    }

    public ProgramVersion(final int major, final int minor, final int patch) {
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