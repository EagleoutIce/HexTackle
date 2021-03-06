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
		try (final var is = ResourceLoader.getInternalFileInputStream(from)) {
			properties.load(is);
			return Optional.of(
					new PackageMetaInformation(properties.getProperty("groupId"), properties.getProperty("artifactId"),
											   new PackageVersion(properties.getProperty("version"))));
		} catch (IOException | NullPointerException ex) {
			// somewhat expected for internal
			log.error("Requesting Package meta for: {}", ex.getMessage());
		}
		return Optional.empty();
	}
}
