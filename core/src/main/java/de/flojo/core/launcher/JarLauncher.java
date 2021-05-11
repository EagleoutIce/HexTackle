package de.flojo.core.launcher;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class JarLauncher {
	public static final String JAVA_JVM_HOME = ProcessHandle.current().info().command().orElse("java");

	private JarLauncher() {}

	public static void launch(final String javaArg, final String jar, final String... jarArgs) throws IOException {
		launch(new String[] {javaArg}, jar, jarArgs);
	}

	public static void launch(final String[] javaArgs, final String jar, final String[] jarArgs) throws IOException {
		log.debug("Launching new jar ({}) with java home \"{}\"", jar, JAVA_JVM_HOME);

		final var command = new ArrayList<String>();
		command.add(JAVA_JVM_HOME);
		for (final var javaArg: javaArgs) {
			if (!javaArg.isBlank())
				command.add(javaArg);
		}
		command.add("-jar");
		command.add(jar);
		command.addAll(Arrays.asList(jarArgs));
		log.info("Starting with: {}", command);
		final var processBuilder = new ProcessBuilder(command);
		processBuilder.start();
	}

}
