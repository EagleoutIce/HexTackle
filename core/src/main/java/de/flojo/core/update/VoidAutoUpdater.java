package de.flojo.core.update;

import de.flojo.core.update.fetchers.IFetchUpdate;

public class VoidAutoUpdater extends AbstractAutoUpdater{

    public VoidAutoUpdater() {
        updateVersionState(NewVersionState.DISABLED);
    }

    @Override
    public IUpdateProgress update() {
        throw new UnsupportedOperationException("Update is not available on an void updater.");
    }

    @Override
    public IFetchUpdate fetch() {
        throw new UnsupportedOperationException("Fetch is not available on an void updater.");
    }
}
