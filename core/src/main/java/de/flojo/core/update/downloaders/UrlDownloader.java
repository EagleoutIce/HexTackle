package de.flojo.core.update.downloaders;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

@Slf4j
public class UrlDownloader implements IDownloadUpdate {
	final String source;

	public UrlDownloader(final String source) {
		this.source = source;
	}


	@SneakyThrows
	@Override
	public void downloadTo(final String targetPath) {
		log.info("Download from: \"{}\" to: \"{}\"", source, targetPath);
		FileUtils.copyURLToFile(new URL(source), new File(targetPath)
		);
	}
}
