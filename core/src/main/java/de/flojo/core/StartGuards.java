package de.flojo.core;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class StartGuards {

    public static final String MACOS_GUARD = "-guard-macos";

    private StartGuards() {
    }

    // true if restarted
    public static boolean guardMacOs(String[] args) {
        log.info("Starting with guard for MacOs");
        if (OperatingSystem.getOperatingSystemType().equals(OperatingSystemType.MAC_OS) && !hasGuard(args,
                                                                                                     MACOS_GUARD)) {
            try {
                log.info("Restarting!");
                restartWith("-XstartOnFirstThread", args, MACOS_GUARD);
            } catch (URISyntaxException | IOException e) {
                log.error("MacOS guard failed", e);
                return false;
            }
            return true;
        }
        return false;
    }

    private static void restartWith(String extraPre, String[] args,
                                    String extraArg) throws URISyntaxException, IOException {
        final var currentPath = StartGuards.class
                .getProtectionDomain()
                .getCodeSource().getLocation()
                .toURI().getPath()
                .replace('/', File.separator.charAt(0));
        final var command = new ArrayList<String>();
        command.add("java");
        if (!extraPre.isBlank())
            command.add(extraPre);
        command.add("-jar");
        command.add(currentPath);
        command.addAll(Arrays.asList(args));
        command.add(extraArg);
        log.info("Starting with: {}", command);
        final var processBuilder = new ProcessBuilder(command);
        processBuilder.start();
    }

    private static boolean hasGuard(String[] args, String guard) {
        return Arrays.asList(args).contains(guard);
    }
}
