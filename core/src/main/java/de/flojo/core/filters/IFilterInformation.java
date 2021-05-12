package de.flojo.core.filters;

@FunctionalInterface
public interface IFilterInformation<T, E> {
	FilterReport<E> filter(T data);
}
