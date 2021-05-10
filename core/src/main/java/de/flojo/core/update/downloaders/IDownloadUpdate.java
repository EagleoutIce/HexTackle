package de.flojo.core.update.downloaders;

import java.nio.file.Path;

public interface IDownloadUpdate {
	void downloadTo(final Path fullTargetPath);
}
