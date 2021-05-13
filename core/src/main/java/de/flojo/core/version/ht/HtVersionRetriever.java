package de.flojo.core.version.ht;

import de.flojo.core.launcher.JarMetaProvider;

public class HtVersionRetriever {

	private static final IRetrieveHtMeta RETRIEVE_HT_META = JarMetaProvider
			.runsFromJar() ? new HtJarMetaReceiver() : new HtIdeMetaReceiver();

	private HtVersionRetriever() {
	}

	public static IRetrieveHtMeta getRetriever() {
		return RETRIEVE_HT_META;
	}
}
