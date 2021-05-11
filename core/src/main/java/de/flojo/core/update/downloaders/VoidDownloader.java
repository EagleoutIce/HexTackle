package de.flojo.core.update.downloaders;

import java.nio.file.Path;

public class VoidDownloader implements IDownloadUpdate {
	@Override
	public Path downloadTo(final Path fullTargetPath) {
		throw new UnsupportedOperationException("There is nothing to download for " + fullTargetPath);
	}
}
