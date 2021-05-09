package de.flojo.core.update;

import java.io.IOException;

public interface IListenToNewVersionState {
    void update(NewVersionState newVersionState);
}
