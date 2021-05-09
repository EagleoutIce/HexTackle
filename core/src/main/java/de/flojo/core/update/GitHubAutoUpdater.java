package de.flojo.core.update;

import de.flojo.core.update.fetchers.IFetchUpdate;
import de.flojo.core.update.fetchers.UrlFetcher;
import de.flojo.core.update.program.ProgramData;
import de.flojo.core.update.program.RegexDefaultProgramPatternMatcher;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class GitHubAutoUpdater extends AbstractAutoUpdater {

    private static final RegexDefaultProgramPatternMatcher matcher = new RegexDefaultProgramPatternMatcher();

    private final ProgramData programData;
    private GHContent updateData = null;

    public GitHubAutoUpdater(final String jarPath) {
        programData = matcher.match(jarPath);
        if(ProgramData.isValid(programData)) {
            log.debug("Start fetching updates for github auto updater...");
            new Thread(this::checkForPossibleUpdate).start();
        } else {
            log.error("Fetch update failed, got invalid programData for jarPath {}", jarPath);
        }
    }

    private void checkForPossibleUpdate() {
        log.debug("Started fetching.");
        try {
            identifyPresentVersion();
        } catch (IOException ex) {
            log.warn("Failed to fetch version", ex);
            updateVersionState(NewVersionState.FAILED_TO_FETCH);
        }
    }

    private void identifyPresentVersion() throws IOException {
        final var gitHub = GitHub.connect();
        final var repository = gitHub.getUser("EagleoutIce").getRepository("HexTackle");
        final var contentList = repository.getDirectoryContent("./", repository.getBranch("build").getSHA1());
        for (final var content : contentList) {
            final var data = matcher.match(content.getName());
            if(ProgramData.isValid(data) && Objects.equals(data.getBaseName(),  programData.getBaseName())) {
                checkUpdateFor(data, content);
                return;
            }
        }
        log.warn("Found no valid jarfile to update on github");
        updateVersionState(NewVersionState.FAILED_TO_FETCH);
    }

    private void checkUpdateFor(ProgramData other, GHContent content) {
        log.info("Check to update with {}", other);
        if(other.getVersion().isNewerThan(programData.getVersion())) {
            log.info("There is an update available");
            updateData = content;
            updateVersionState(NewVersionState.PRESENT);
        } else {
            updateVersionState(NewVersionState.NONE_PRESENT);
        }
    }


    @Override
    public IUpdateProgress update() {
        return null;
    }

    @Override
    public IFetchUpdate fetch() throws IOException {
        return new UrlFetcher(updateData.getDownloadUrl());
    }
}
