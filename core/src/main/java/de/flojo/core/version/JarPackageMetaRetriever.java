package de.flojo.core.version;

import de.flojo.core.ResourceLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class JarPackageMetaRetriever implements IRetrievePackageMeta {
	@Override
	public Optional<IPackageMetaInformation> getPackage(String from) {
		final var properties = new Properties();
		try {
			properties.load(ResourceLoader.getFileInputStream(from));
			return Optional.of(
					new PackageMetaInformation(properties.getProperty("groupId"), properties.getProperty("artifactId"),
											   new PackageVersion(properties.getProperty("version"))));
		} catch (IOException | NullPointerException ex) {
			log.error("Requesting Package meta", ex);
		}
		return Optional.empty();
	}
}
