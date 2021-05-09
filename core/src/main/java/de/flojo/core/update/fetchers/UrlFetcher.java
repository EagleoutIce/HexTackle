package de.flojo.core.update.fetchers;

public class UrlFetcher implements IFetchUpdate {
    final String source;

    public UrlFetcher(final String source) {
        this.source = source;
    }


    @Override
    public void fetch(final String targetPath) {

    }
}
