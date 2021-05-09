package de.flojo.core.update;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoUpdateProgress implements IUpdateProgress {

    @Override
    public void run() {
        log.info("Called run on no update progress; Nothing to be done.");
    }
}
