package de.flojo.core.launcher;

import de.flojo.core.update.AutoUpdaterFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URISyntaxException;

@Slf4j
public final class JarMetaProvider {
	public static final String JAR_PATH;
	public static final String JAR_SUFFIX = ".jar";

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
		log.info("Using Jar core {}", JAR_PATH);
	}

	private JarMetaProvider() {
	}

	public static boolean runsFromJar() {
		return JAR_PATH.endsWith(JAR_SUFFIX);
	}

}
