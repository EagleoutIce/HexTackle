package de.flojo.core.update;

import de.flojo.core.update.downloaders.IDownloadUpdate;
import de.flojo.core.update.program.ProgramData;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class AbstractAutoUpdater {

	private final List<IListenToNewVersionState> listeners = Collections.synchronizedList(new ArrayList<>());
	protected NewVersionState newVersionState = NewVersionState.UNKNOWN;

	public NewVersionState getNewVersionState() {
		return newVersionState;
	}

	/// Start fetching for any updates
	public abstract void fetch();

	protected void updateVersionState(final NewVersionState newVersionState) {
		log.debug("Got Update for new version: {}", newVersionState);
		this.newVersionState = newVersionState;
		for (final var listener : listeners) {
			listener.update(newVersionState);
		}
	}

	public void registerNewVersionListener(final IListenToNewVersionState listener) {
		listeners.add(listener);
	}

	public boolean deleteNewVersionListener(final IListenToNewVersionState listener) {
		return listeners.remove(listener);
	}

	public abstract IDownloadUpdate getDownloader(final boolean force) throws IOException;
}
