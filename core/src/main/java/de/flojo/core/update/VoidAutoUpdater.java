package de.flojo.core.update;

import de.flojo.core.update.downloaders.IDownloadUpdate;

public class VoidAutoUpdater extends AbstractAutoUpdater {

	private final String jarPath;

	public VoidAutoUpdater(final String jarPath) {
		this.jarPath = jarPath;
	}

	@Override
	public void fetch() {
		updateVersionState(NewVersionState.DISABLED);
	}

	@Override
	public String getSourcePath() {
		return jarPath;
	}

	@Override
	public IDownloadUpdate getDownloader(final boolean force) {
		throw new UnsupportedOperationException("Download is not available on an void updater.");
	}
}
