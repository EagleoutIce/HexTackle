package de.flojo.core;

public interface IFactory<T> {
	/**
	 * Method to be called when the desired object should be created.
	 */
	T create();
}
