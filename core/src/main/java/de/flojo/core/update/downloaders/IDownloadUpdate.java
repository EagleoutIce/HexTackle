package de.flojo.core.update.downloaders;

import java.nio.file.Path;

public interface IDownloadUpdate {
	Path downloadToParent(final Path fullTargetPath);
}
