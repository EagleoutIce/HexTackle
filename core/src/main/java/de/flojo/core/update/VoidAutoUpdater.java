package de.flojo.core.update;

import de.flojo.core.update.downloaders.IDownloadUpdate;

public class VoidAutoUpdater extends AbstractAutoUpdater {

	@Override
	public void fetch() {
		updateVersionState(NewVersionState.DISABLED);
	}

	@Override
	public IDownloadUpdate getDownloader(final boolean force) {
		throw new UnsupportedOperationException("Download is not available on an void updater.");
	}
}
