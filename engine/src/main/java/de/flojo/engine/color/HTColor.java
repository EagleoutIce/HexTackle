package de.flojo.engine.color;

import com.badlogic.gdx.graphics.Color;

// Bridges between awt Color Functions and libgdx
public class HTColor extends Color {

	public HTColor() {
	}

	public HTColor(final int rgba8888) {
		super(rgba8888);
	}

	public HTColor(final float r, final float g, final float b) {
		this(r, g, b, 1f);
	}

	public HTColor(final float r, final float g, final float b, final float a) {
		super(r, g, b, a);
	}

	public HTColor(final HTColor color) {
		super(color);
	}

	@SuppressWarnings("java:S100")
	public static int HSBtoRGB(final float hue, final float saturation, final float brightness) {
		return java.awt.Color.HSBtoRGB(hue, saturation, brightness);
	}

	public float getRed() {
		return r;
	}

	public float getGreen() {
		return r;
	}

	public float getBlue() {
		return b;
	}

	public float getAlpha() {
		return a;
	}

}
