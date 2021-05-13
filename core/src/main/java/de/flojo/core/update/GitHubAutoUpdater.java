package de.flojo.core.update;

import de.flojo.core.update.downloaders.IDownloadUpdate;
import de.flojo.core.update.downloaders.UrlDownloader;
import de.flojo.core.update.downloaders.VoidDownloader;
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

	private static final String REPO_OWNER = "EagleoutIce";
	private static final String REPO_NAME = "HexTackle";
	private static final String REPO_ROOT = "./";
	private static final String TARGET_BRANCH = "build";

	private final String jarPath;
	private final ProgramData programData;
	private GHContent updateData = null;

	public GitHubAutoUpdater(final String jarPath) {
		this.jarPath = jarPath;
		programData = matcher.match(jarPath,
									pattern -> log.error("Error when matching pattern \"{}\" on jar \"{}\"", pattern,
														 jarPath));
	}

	public String getSourcePath() {
		return jarPath;
	}

	private void checkForPossibleUpdate() {
		log.debug("Started fetching.");
		try {
			identifyPresentVersion();
		} catch (IOException ex) {
			log.warn("Failed to getDownloader version", ex);
			updateVersionState(NewVersionState.FAILED_TO_FETCH);
		}
	}

	private void identifyPresentVersion() throws IOException {
		final var gitHub = GitHub.connectAnonymously();
		log.trace("Connected to GitHub");
		final var rateLimit = gitHub.getRateLimit();
		log.info("Rate Limit: {}", rateLimit);
		if (rateLimit.getCore().getRemaining() <= 0) {
			updateVersionState(NewVersionState.FAILED_TO_FETCH);
			return;
		}
		final var user = gitHub.getUser(REPO_OWNER);
		log.trace("Got user: {}", user.getName());
		final var repository = user.getRepository(REPO_NAME);
		log.trace("Repository: {}", repository.getName());
		final var contentList = repository.getDirectoryContent(REPO_ROOT,
															   repository.getBranch(TARGET_BRANCH).getSHA1());
		log.trace("Fetched contents");
		for (final var content : contentList) {
			if (tryGivenContentForVersion(content)) return;
		}
		log.warn("Found no valid jarfile to update on GitHub");
		updateVersionState(NewVersionState.FAILED_TO_FETCH);
	}

	private boolean tryGivenContentForVersion(final GHContent content) {
		// we silence the matcher, as these errors are expected
		final var data = matcher.match(content.getName(), pattern -> {
		});
		if (ProgramData.isValid(data) && Objects.equals(data.getBaseName(), programData.getBaseName())) {
			checkUpdateFor(data, content);
			return true;
		}
		return false;
	}

	private void checkUpdateFor(ProgramData other, GHContent content) {
		log.info("Check to update with {}", other);
		if (other.getVersion().isNewerThan(programData.getVersion())) {
			log.info("There is an update available");
			updateData = content;
			updateVersionState(NewVersionState.PRESENT);
		} else {
			log.info("There is no update available");
			updateData = content;
			updateVersionState(other.getVersion().equals(
					programData.getVersion()) ? NewVersionState.SAME_PRESENT : NewVersionState.NONE_PRESENT);
		}
	}

	@Override
	public void fetch() {
		if (ProgramData.isValid(programData)) {
			log.debug("Start fetching updates for github auto updater...");
			new Thread(this::checkForPossibleUpdate).start();
			// TODO: executor service timeout
		} else {
			updateVersionState(NewVersionState.DISABLED);
			log.error("Fetch update failed, got invalid programData for jarPath {}", this.jarPath);
		}
	}

	@Override
	public IDownloadUpdate getDownloader(final boolean force) {
		if (force || newVersionState.equals(NewVersionState.PRESENT)) {
			// TODO: change
			return new UrlDownloader("https://github.com/EagleoutIce/HexTackle/raw/build/" + updateData.getName());
		} else {
			return new VoidDownloader();
		}
	}
}
