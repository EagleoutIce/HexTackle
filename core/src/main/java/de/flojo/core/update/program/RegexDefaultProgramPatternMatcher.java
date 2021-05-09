package de.flojo.core.update.program;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class RegexDefaultProgramPatternMatcher implements IAmProgramPatternMatcher {

    private static final Pattern PROGRAM_NAME = Pattern.compile("(.*/)?(?<name>[^-]*)-(?<major>\\d*)(\\.(?<minor>\\d*)(\\.(?<patch>\\d*))?)?\\.jar");

    @Override
    public ProgramData match(final CharSequence path) {
        final var regexMatch = PROGRAM_NAME.matcher(path);
        if(!regexMatch.matches()) {
            log.debug("Unable to match regex \"{}\" in \"{}\".", PROGRAM_NAME, path);
            return ProgramData.INVALID;
        }
        final ProgramVersion programVersion;
        try {
            programVersion = new ProgramVersion(regexMatch.group("major"), regexMatch.group("minor"), regexMatch.group("patch"));
        } catch (ProgramVersionIdentificationException ignored) {
            return ProgramData.INVALID;
        }
        final var programData = new ProgramData(regexMatch.group("name"), programVersion);
        log.debug("Identified program data: {}", programData);
        return programData;
    }
}
