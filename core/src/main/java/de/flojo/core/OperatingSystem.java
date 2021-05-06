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
            return OperatingSystemType.MacOS;
        } else if (operatingSystemString.contains("win")) {
            return OperatingSystemType.Windows;
        } else if (operatingSystemString.contains("nux")) {
            return OperatingSystemType.Linux;
        } else {
            return OperatingSystemType.Unknown;
        }
    }
}
