package de.flojo.core.update;

@FunctionalInterface
public interface IDescribePatternError {
    void onError(String patternUsed);
}
