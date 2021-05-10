package de.flojo.core.update;


import de.flojo.core.IFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Automatically update Java Application
 */
@Slf4j
public class AutoUpdaterFactory implements IFactory<AbstractAutoUpdater> {

	public static final String JAR_PATH;

	static {
		String bufferedJarPath;
		try {
			bufferedJarPath = new File(AutoUpdaterFactory.class.getProtectionDomain().getCodeSource().getLocation()
														 .toURI()).getPath();
		} catch (URISyntaxException ex) {
			log.error("On Retrieving jar path. ", ex);
			bufferedJarPath = "";
		}
		JAR_PATH = bufferedJarPath;
	}

	private static final String JAR_END = ".jar";

	public AutoUpdaterFactory() {
		log.info("Using Jar-Path: {}", JAR_PATH);
	}

	public AbstractAutoUpdater create() {
		if (!JAR_PATH.endsWith(JAR_END)) {
			log.warn("Path is not a jar file. Update-checks are disabled.");
			return new VoidAutoUpdater();
		} else {
			return new GitHubAutoUpdater(JAR_PATH);
		}
	}
}
