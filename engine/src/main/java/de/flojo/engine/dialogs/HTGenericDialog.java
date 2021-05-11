package de.flojo.engine.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HTGenericDialog extends Dialog {
	public static final BitmapFont DEFAULT_FONT = new BitmapFont();
	public static final WindowStyle DEFAULT_STYLE = new WindowStyle(DEFAULT_FONT, Color.BLACK, new SpriteDrawable(new Sprite(createTexture(600,600, Color.BLACK))));

	private static Texture createTexture(final int width, final int height, final Color color) {
		final var pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		pixmap.setColor(color.r, color.g, color.b, 1f);
		pixmap.fillRectangle(0, 0, width, height);

		return new Texture(pixmap);
	}

	public HTGenericDialog(final String title) {
		this(title, DEFAULT_STYLE);
	}

	public HTGenericDialog(final String title, final WindowStyle style) {
		super(title, style);
		setup();
	}

	private void setup() {
		setModal(true);
		setMovable(true);
		setResizable(false);
	}

	@Override
	public HTGenericDialog text(final String text) {
		final var label = new Label(text, new Label.LabelStyle(DEFAULT_FONT, Color.WHITE));
		label.setAlignment(Align.center);
		label.setWrap(true);
		label.setWidth(getWidth());
		text(label);
		return this;
	}

	// TODO: make more flexible
	public HTGenericDialog setYesButton(String buttonText, InputListener listener) {
		final var textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = DEFAULT_FONT;
		textButtonStyle.fontColor = Color.WHITE;

		final var button = new TextButton(buttonText, textButtonStyle);
		button.setSize(80, 44);
		button.addListener(listener);
		button.padLeft(10);
		button.padRight(10);
		button(button);

		return this;
	}

	// The "NO" button has a different design compared to the "YES" button
	// In this example it's a transparent background with a white text
	public HTGenericDialog setNoButton(String buttonText, InputListener listener) {
		final var textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = DEFAULT_FONT;
		textButtonStyle.fontColor = Color.WHITE;

		final var button = new TextButton(buttonText, textButtonStyle);
		button.setSize(80, 44);
		button.addListener(listener);
		button.padLeft(10);
		button.padRight(10);
		button(button);

		return this;
	}

}
