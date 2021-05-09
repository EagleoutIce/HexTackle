package de.flojo.core;

import java.util.Locale;

public class OperatingSystem {

    private static OperatingSystemType bufferedOperatingSystem;

    private OperatingSystem() {
    }

    public static OperatingSystemType getOperatingSystemType() {
        if (bufferedOperatingSystem != null)
            return bufferedOperatingSystem;

        bufferedOperatingSystem = analyzeOperatingSystemType();

        return bufferedOperatingSystem;
    }

    private static OperatingSystemType analyzeOperatingSystemType() {
        final var operatingSystemString = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (operatingSystemString.contains("mac") || operatingSystemString.contains("darwin")) {
            return OperatingSystemType.MAC_OS;
        } else if (operatingSystemString.contains("win")) {
            return OperatingSystemType.WINDOWS;
        } else if (operatingSystemString.contains("nux")) {
            return OperatingSystemType.LINUX;
        } else {
            return OperatingSystemType.UNKNOWN;
        }
    }
}
