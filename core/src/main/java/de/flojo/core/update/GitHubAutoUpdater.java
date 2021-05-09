package de.flojo.core.update;

import de.flojo.core.update.program.ProgramData;
import de.flojo.core.update.program.RegexDefaultProgramPatternMatcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubAutoUpdater extends AbstractAutoUpdater {

    private static final RegexDefaultProgramPatternMatcher matcher = new RegexDefaultProgramPatternMatcher();

    private final String jarPath;
    private final ProgramData programData;

    public GitHubAutoUpdater(final String jarPath) {
        this.jarPath = jarPath;
        programData = matcher.match(jarPath);
    }

    @Override
    IUpdateProgress update() {
        return null;
    }
}
