package de.flojo.vcore.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import de.flojo.core.version.ht.HtVersionRetriever;
import de.flojo.engine.HTScreenAdapter;
import de.flojo.engine.IAmGameCore;
import de.flojo.engine.color.HTColor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RgbCycleScreen extends HTScreenAdapter {
	private final Label label;
	private int v = 0;
	private long last = System.currentTimeMillis();
	private HTColor lastColor = new HTColor(0, 0, 0);

	public RgbCycleScreen(final IAmGameCore gameCore) {
		super(gameCore);
		// TODO style management
		final var labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont();
		labelStyle.fontColor = Color.BLACK;
		final var version = HtVersionRetriever.getRetriever().getMeta("core");
		label = new Label("Core Version " + version.getVersion().getVersionString(), labelStyle);
		centerLabel(gameCore);
		stage.addActor(label);
	}

	private void centerLabel(final IAmGameCore gameCore) {
		label.setPosition((gameCore.getViewport().getWorldWidth() - label.getWidth()) / 2f,
						  (gameCore.getViewport().getWorldHeight() - label.getHeight()) / 2f);
	}

	@Override
	public void resize(final int width, final int height) {
		centerLabel(getGameCore());
	}

	@Override
	public void show() {
		log.info("Showing rgb cycle screen");
	}

	@Override
	public void render(final float delta) {
		if (System.currentTimeMillis() - last > 15) {
			v = Math.floorMod(v + 1, 256);
			lastColor = new HTColor(HTColor.HSBtoRGB(v / 255f, .75f, .75f));
			last = System.currentTimeMillis();
		}
		ScreenUtils.clear(lastColor.getRed(), lastColor.getGreen(), lastColor.getBlue(), 1f);
		stage.act(delta);
		stage.draw();
	}
}
