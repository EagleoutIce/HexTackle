package de.flojo.core.update.downloaders;

public class VoidDownloader implements IDownloadUpdate {
    @Override
    public void downloadTo(final String targetPath) {
        throw new UnsupportedOperationException("There is nothing to download");
    }
}
