package de.flojo.core.version;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

// TODO: make this more beautiful with istream mfunc
@Slf4j
public class ExternalFilePackageMetaRetriever implements IRetrievePackageMeta {
	@Override
	public Optional<IPackageMetaInformation> getPackage(String from) {
		final var properties = new Properties();
		try (final var is = new InputStreamReader(new FileInputStream(from))) {
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
