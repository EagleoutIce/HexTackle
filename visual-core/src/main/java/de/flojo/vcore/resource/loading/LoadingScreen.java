package de.flojo.vcore.resource.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.flojo.core.update.NewVersionState;
import de.flojo.engine.HTScreenAdapter;
import de.flojo.engine.IAmGameCore;
import de.flojo.vcore.screens.RgbCycleScreen;
import de.flojo.vcore.update.AskForAutoUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadingScreen extends HTScreenAdapter {
	private static final int PROGRESS_BAR_HEIGHT = 50;
	private static final int PROGRESS_BAR_WIDTH = 200;
	private final RgbCycleScreen postScreen; // move to game
	private final AskForAutoUpdate updater;

	private LoadingState loadingState = LoadingState.INITIAL;

	public LoadingScreen(final IAmGameCore gameCore) {
		super(gameCore);
		Gdx.input.setInputProcessor(stage);
		postScreen = new RgbCycleScreen(gameCore);
		updater = new AskForAutoUpdate(stage);
		updater.registerNewDialogListener(newState -> {
			log.info("New version state {}", newState);
			if (!newState.equals(NewVersionState.UNKNOWN))
				loadingState = LoadingState.FINISHED; // TODO: state updater
		});
	}

	@Override
	public void show() {
		log.info("Showing resource loading screen");
		getGameCore().getAssetManager().finishLoading();
		loadingState = LoadingState.RESOURCES;
	}

	@Override
	public void render(final float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		renderProgressbar();
		stage.act(delta);
		updateState();
		stage.draw();
	}

	private void updateState() {
		switch (loadingState) {
			case RESOURCES:
				if (getGameCore().getAssetManager().update()) {
					loadingState = LoadingState.UPDATE;
					updater.fetch();
				}
				break;
			case FINISHED:
				getGameCore().getGame().setScreen(postScreen);
				break;
			default:
		}
	}

	private void renderProgressbar() {
		final var progress = getGameCore().getAssetManager().getProgress();
		final var shapeRenderer = getGameCore().getShapeRenderer();
		// TODO: other progress
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(10, 10, PROGRESS_BAR_WIDTH * progress, PROGRESS_BAR_HEIGHT);
		shapeRenderer.end();
	}

}
