package de.flojo.editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import de.flojo.core.logging.ConsumerPrintStreamBridge;
import de.flojo.core.os.StartGuards;
import de.flojo.vcore.update.AskForAutoUpdate;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;

@Slf4j
public class Main extends ApplicationAdapter {
	int v = 0;
	long last = System.currentTimeMillis();
	Color lastColor = new Color(0, 0, 0);

	public static void main(String[] args) {
		if (StartGuards.guardMacOs(args)) {
			return;
		}
		// Note: this is a default implementation and has to change; we want a button and an information
		// to check for updates; this automatically auto-update anything is pretty bad :D

		final var config = new Lwjgl3ApplicationConfiguration();
		log.debug("Setup configuration");
		config.setTitle("HexTackle - Editor");
		config.setIdleFPS(30);
		config.setMaxNetThreads(8);
		config.enableGLDebugOutput(true, new ConsumerPrintStreamBridge(log::debug));
		log.info("Starting editor ({})", config);
		new Lwjgl3Application(new Main(), config);
	}

	Stage stage;
	AskForAutoUpdate updater;
	BitmapFont font;
	SpriteBatch batch;

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		updater = new AskForAutoUpdate(stage);
		updater.fetch();
		batch = new SpriteBatch();
		font = new BitmapFont();
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
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		font.draw(batch, "Update State: " + updater.getUpdater().getNewVersionState(), 10, 15);
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
		batch.dispose();
		font.dispose();
	}
}
