package de.flojo.core.logging;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.function.Consumer;

public class ConsumerPrintStreamBridge extends PrintStream {

	private final Consumer<String> writer;

	public ConsumerPrintStreamBridge(final Consumer<String> writeMessage) {
		super(new VoidOutputStream());
		writer = writeMessage;
	}

	@Override
	public void flush() {
		// do nothing
	}

	@Override
	public void close() {
		// do nothing
	}

	@Override
	public boolean checkError() {
		// do nothing
		return false;
	}

	@Override
	protected void setError() {
		// do nothing
	}

	@Override
	protected void clearError() {
		// do nothing
	}

	@Override
	public void write(final int b) {
		writer.accept(Integer.toString(b));
	}

	@Override
	public void write(final byte[] buf, final int off, final int len) {
		// TODO
		throw new UnsupportedOperationException("Currently not implemented");
	}

	@Override
	public void write(final byte[] buf) {
		// TODO
		throw new UnsupportedOperationException("Currently not implemented");
	}

	@Override
	public void writeBytes(final byte[] buf) {
		// TODO
		throw new UnsupportedOperationException("Currently not implemented");
	}

	@Override
	public void print(final boolean b) {
		writer.accept(Boolean.toString(b));
	}

	@Override
	public void print(final char c) {
		writer.accept(Character.toString(c));
	}

	@Override
	public void print(final int i) {
		writer.accept(Integer.toString(i));
	}

	@Override
	public void print(final long l) {
		writer.accept(Long.toString(l));
	}

	@Override
	public void print(final float f) {
		writer.accept(Float.toString(f));
	}

	@Override
	public void print(final double d) {
		writer.accept(Double.toString(d));
	}

	@Override
	public void print(final char[] s) {
		writer.accept(new String(s));
	}

	@Override
	public void print(final String s) {
		writer.accept(s);
	}

	@Override
	public void print(final Object obj) {
		writer.accept(obj != null ? obj.toString() : null);
	}

	@Override
	public void println() {
		writer.accept(System.lineSeparator());
	}

	@Override
	public void println(final boolean x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final char x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final int x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final long x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final float x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final double x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final char[] x) {
		writer.accept(new String(x) + System.lineSeparator());
	}

	@Override
	public void println(final String x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public void println(final Object x) {
		writer.accept(x + System.lineSeparator());
	}

	@Override
	public PrintStream printf(final String format, final Object... args) {
		writer.accept(String.format(format, args));
		return this;
	}

	@Override
	public PrintStream printf(final Locale l, final String format, final Object... args) {
		writer.accept(String.format(l, format, args));
		return this;
	}

	@Override
	public PrintStream format(final String format, final Object... args) {
		writer.accept(String.format(format, args));
		return this;
	}

	@Override
	public PrintStream format(final Locale l, final String format, final Object... args) {
		writer.accept(String.format(l,format, args));
		return this;
	}

	@Override
	public PrintStream append(final CharSequence csq) {
		writer.accept(csq.toString());
		return this;
	}

	@Override
	public PrintStream append(final CharSequence csq, final int start, final int end) {
		throw new UnsupportedOperationException("Currently not implemented");
	}

	@Override
	public PrintStream append(final char c) {
		writer.accept(Character.toString(c));
		return this;
	}
}
