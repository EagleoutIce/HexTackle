package de.flojo.core.update;


import de.flojo.core.IFactory;
import de.flojo.core.launcher.JarMetaProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * Automatically update Java Application
 */
@Slf4j
public class AutoUpdaterFactory implements IFactory<AbstractAutoUpdater> {

	private final String usedPath;

	public AutoUpdaterFactory() {
		this(JarMetaProvider.JAR_PATH);
	}

	public AutoUpdaterFactory(final String usePath) {
		this.usedPath = usePath;
		log.info("Using Path: {}", JarMetaProvider.JAR_PATH);
	}

	public String getUsedPath() {
		return usedPath;
	}

	public AbstractAutoUpdater create() {
		if (!usedPath.endsWith(JarMetaProvider.JAR_SUFFIX)) {
			log.warn("Path is not a jar file. Update-checks are disabled.");
			return new VoidAutoUpdater(usedPath);
		} else {
			return new GitHubAutoUpdater(usedPath);
		}
	}
}
