package dummies.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;

@Slf4j
public class RgbCycleScreen extends ScreenAdapter {
	private int v = 0;
	private long last = System.currentTimeMillis();
	private Color lastColor = new Color(0, 0, 0);

	@Override
	public void show() {
		log.info("Showing rgb cycle screen");
	}

	@Override
	public void render(final float delta) {
		if (System.currentTimeMillis() - last > 25) {
			v = Math.floorMod(v + 1, 256);
			lastColor = new Color(Color.HSBtoRGB(v / 255f, .75f, .75f));
			last = System.currentTimeMillis();
		}
		ScreenUtils.clear(lastColor.getRed() / 255f, lastColor.getGreen() / 255f, lastColor.getBlue() / 255f, 1f);
	}
}
