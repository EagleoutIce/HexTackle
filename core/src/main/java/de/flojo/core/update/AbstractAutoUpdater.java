package de.flojo.core.update;

import de.flojo.core.update.fetchers.IFetchUpdate;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class AbstractAutoUpdater {

    protected NewVersionState newVersionState = NewVersionState.UNKNOWN;

    private final List<IListenToNewVersionState> listeners = Collections.synchronizedList(new ArrayList<>());

    public NewVersionState getNewVersionState() {
        return newVersionState;
    }

    protected void updateVersionState(NewVersionState newVersionState){
        log.debug("Got Update for new version: {}", newVersionState);
        for (final var listener: listeners) {
            listener.update(newVersionState);
        }
        this.newVersionState = newVersionState;
    }

    public void registerNewVersionListener(IListenToNewVersionState listener) {
        listeners.add(listener);
    }
    public boolean deleteNewVersionListener(IListenToNewVersionState listener) {
        return  listeners.remove(listener);
    }

    public abstract IUpdateProgress update();

    public abstract IFetchUpdate fetch() throws IOException;
}
