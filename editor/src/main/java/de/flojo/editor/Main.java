package de.flojo.editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import de.flojo.core.os.StartGuards;
import de.flojo.core.update.AbstractAutoUpdater;
import de.flojo.core.update.AutoUpdaterFactory;
import de.flojo.core.update.NewVersionState;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class Main extends ApplicationAdapter {
	private static AbstractAutoUpdater updater;
	int v = 0;
	long last = System.currentTimeMillis();
	Color lastColor = new Color(0, 0, 0);

	public static void main(String[] args) {
		if (StartGuards.guardMacOs(args)) {
			return;
		}
		updater = new AutoUpdaterFactory().create();
		// Note: this is a default implementation and has to change; we want a button and an information
		// to check for updates; this automatically auto-update anything is pretty bad :D
		registerVersionAutoUpdateListener(updater);
		updater.fetch();

		final var config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("HexTackle - Editor");
		log.info("Starting editor ({})", config);
		new Lwjgl3Application(new Main(), config);
	}

	private static void registerVersionAutoUpdateListener(final AbstractAutoUpdater updater) {
		updater.registerNewVersionListener((newVersionState -> {
			// warning: potential downgrade for same!
			if (newVersionState.equals(NewVersionState.PRESENT) || newVersionState.equals(
					NewVersionState.SAME_PRESENT)) {
				try {
					var downloader = updater.getDownloader(true);
					log.info("Forcing update to: {} in 2s", AutoUpdaterFactory.JAR_PATH);
					Thread.sleep(2000);
					// this should be buffered/changed too
					downloader.downloadTo(Path.of(AutoUpdaterFactory.JAR_PATH),
										  newVersionState.equals(NewVersionState.PRESENT));
				} catch (IOException e) {
					log.error("Unable to retrieve downloader new Jar. ", e);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}));
	}
	// todo: sage and label; render correctly

	@Override
	public void render() {
		super.render();
		if (System.currentTimeMillis() - last > 14) {
			v = Math.floorMod(v + 1, 256);
			lastColor = new Color(Color.HSBtoRGB(v / 255f, .75f, .75f));
		}
		ScreenUtils.clear(lastColor.getRed() / 255f, lastColor.getGreen() / 255f, lastColor.getBlue() / 255f, 1f);
		final var batch = new SpriteBatch();
		final var font = new BitmapFont();
		batch.begin();
		font.draw(batch, "Update State: " + updater.getNewVersionState(), 10, 15);
		batch.end();
	}
}
