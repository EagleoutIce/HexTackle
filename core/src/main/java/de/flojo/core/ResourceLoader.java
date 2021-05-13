package de.flojo.core;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
public class ResourceLoader {
	// TODO :)

	// Hide the default one
	private ResourceLoader() {
	}

	/**
	 * Just a simple one-liner for accessing internal resources. You can theoretically extend this class to exchange
	 * meaning.
	 *
	 * @param path The wanted internal Path
	 *
	 * @return input stream if 'gettable'
	 */
	public static InputStream getInternalFileInputStream(String path) {
		log.debug("Retrieving for: {}", path);
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}

	/**
	 * This will load a File from resources and return the Stream for you to handle
	 *
	 * @param path  the path you desire
	 * @param eater will be the function capable of transforming the file-data to a Type you desire. Please note, that
	 *              you cannot return the file-resource as we will do everything to close it - enlighten your fire
	 * @param <T>   Type for conversion
	 *
	 * @return the Stream you admire
	 *
	 * @throws IOException if the acquire conspire'
	 */
	public static <T> T getFile(String path, Function<Stream<String>, T> eater) throws IOException {
		try (final var is = getInternalFileInputStream(path)) {
			// We want to get one String without nasty newlines :D
			final var isReader = new InputStreamReader(Objects.requireNonNull(is));
			final var bufferedReader = new BufferedReader(isReader);
			Stream<String> dataStream = bufferedReader.lines();
			final var dataT = eater.apply(dataStream);
			isReader.close();
			bufferedReader.close();
			return dataT;
		} catch (IOException ex) {
			log.error("While loading resource", ex);
			throw ex;
		}
	}

	/**
	 * Returns array of lines in a (internal) file
	 *
	 * @param path the path to the file you desire
	 *
	 * @return All lines in a File as array
	 *
	 * @throws IOException if the acquire conspire'
	 */
	public static String[] getFileLines(String path) throws IOException {
		return ResourceLoader.getFile(path, s -> s.toArray(String[]::new));
	}

}
