package de.flojo.core;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class StartGuards {
    private StartGuards() {}

    public static final String MACOS_GUARD = "-guard-macos";

    // true if restarted
    public static boolean guardMacOs(String[] args) {
        if(OperatingSystem.getOperatingSystemType().equals(OperatingSystemType.MacOS) && !hasGuard(args, MACOS_GUARD)) {
            try {
                System.out.println("Restarting!");
                restartWith("-XstartOnFirstThread", args, MACOS_GUARD);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace(); // TODO: logger
                return false;
            }
            return true;
        }
        return false;
    }

    private static void restartWith(String extraPre, String[] args, String extraArg) throws URISyntaxException, IOException {
        final var currentPath = StartGuards.class
                .getProtectionDomain()
                .getCodeSource().getLocation()
                .toURI().getPath()
                .replace('/', File.separator.charAt(0));
        final var command = new ArrayList<String>();
        command.add("java");
        if(!extraPre.isBlank())
            command.add(extraPre);
        command.add("-jar");
        command.add(currentPath);
        command.addAll(Arrays.asList(args));
        command.add(extraArg);
        System.out.println("Starting with: " + command);
        final var processBuilder = new ProcessBuilder(command);
        processBuilder.start();
    }

    private static boolean hasGuard(String[] args, String guard) {
        return Arrays.asList(args).contains(guard);
    }
}
