package de.flojo.core.logging;

import java.io.IOException;
import java.io.OutputStream;

public class VoidOutputStream extends OutputStream {
	@Override
	public void write(final int b) throws IOException {
		// do nothing as it is a void output Stream
	}

	@Override
	public void write(final byte[] b, final int off, final int len) throws IOException {
		// do nothing
	}
}
