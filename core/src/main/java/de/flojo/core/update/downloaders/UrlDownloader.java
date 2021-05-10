package de.flojo.core.update.downloaders;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class UrlDownloader implements IDownloadUpdate {
	final String source;

	public UrlDownloader(final String source) {
		this.source = source;
	}


	@SneakyThrows
	@Override
	public void downloadTo(final Path fullTargetPath) {
		final var targetPath = fullTargetPath.getParent().toString();
		log.info("Download from: \"{}\" to: \"{}\"", source, targetPath);
		final var basenameSource = FilenameUtils.getName(source);
		log.debug("Identified basename \"{}\"", basenameSource);
		FileUtils.copyURLToFile(new URL(source), Path.of(targetPath, basenameSource).toFile());
	}
}
