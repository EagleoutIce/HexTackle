package de.flojo.core.update;

public abstract class AbstractAutoUpdater {

    boolean isThereNewerVersion() {
        return false;
    }

    abstract IUpdateProgress update();
}
