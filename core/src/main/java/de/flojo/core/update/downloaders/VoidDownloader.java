package de.flojo.core.update.downloaders;

import java.nio.file.Path;

public class VoidDownloader implements IDownloadUpdate {
	@Override
	public void downloadTo(final Path fullTargetPath, final boolean erasePrevious) {
		throw new UnsupportedOperationException(
				"There is nothing to download for " + fullTargetPath + " erase: " + erasePrevious);
	}
}
