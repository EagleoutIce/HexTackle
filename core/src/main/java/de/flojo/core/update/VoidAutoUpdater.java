package de.flojo.core.update;

import de.flojo.core.update.downloaders.IDownloadUpdate;

public class VoidAutoUpdater extends AbstractAutoUpdater {

    public VoidAutoUpdater() {
        updateVersionState(NewVersionState.DISABLED);
    }

    @Override
    public void fetch() {
        // Do nothing on fetch
    }

    @Override
    public IDownloadUpdate getDownloader(final boolean force) {
        throw new UnsupportedOperationException("Download is not available on an void updater.");
    }
}
