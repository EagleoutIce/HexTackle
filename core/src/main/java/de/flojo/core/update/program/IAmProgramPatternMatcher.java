package de.flojo.core.update.program;

import de.flojo.core.update.IDescribePatternError;

public interface IAmProgramPatternMatcher {
	ProgramData match(final CharSequence path, IDescribePatternError onError);
}
