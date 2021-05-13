package de.flojo.core.version.ht;

import de.flojo.core.launcher.JarMetaProvider;
import de.flojo.core.version.ExternalFilePackageMetaRetriever;
import de.flojo.core.version.IPackageMetaInformation;
import de.flojo.core.version.InternalPackageMetaInformation;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class HtIdeMetaReceiver extends ExternalFilePackageMetaRetriever implements IRetrieveHtMeta {
	@Override
	public IPackageMetaInformation getMeta(final String groupId, final String artifactId) {
		final var receiveFrom = Path.of(JarMetaProvider.JAR_PATH, "..", "..", "..", artifactId, "target",
										"maven-archiver", "pom.properties");
		log.debug("Ide retrieval path: {}", receiveFrom);
		final var mayPackage = getPackage(receiveFrom.toString());
		return mayPackage.orElseGet(() -> new InternalPackageMetaInformation(groupId, artifactId));
	}
}
