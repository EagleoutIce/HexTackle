package de.flojo.core.update.downloaders;

import java.nio.file.Path;

public interface IDownloadUpdate {
	Path downloadTo(final Path fullTargetPath);
}
