package de.flojo.core.update;

public class VoidAutoUpdater extends AbstractAutoUpdater{

    @Override
    IUpdateProgress update() {
        return null;
    }
}
