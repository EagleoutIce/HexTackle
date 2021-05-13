package de.flojo.core.version.ht;

import de.flojo.core.version.IPackageMetaInformation;
import de.flojo.core.version.InternalPackageMetaInformation;
import de.flojo.core.version.JarPackageMetaRetriever;

public class HtJarMetaReceiver extends JarPackageMetaRetriever implements IRetrieveHtMeta {
	@Override
	public IPackageMetaInformation getMeta(final String groupId, final String artifactId) {
		final var receiveFrom = "META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties";
		final var mayPackage = getPackage(receiveFrom);
		return mayPackage.orElseGet(() -> new InternalPackageMetaInformation(groupId, artifactId));
	}
}
