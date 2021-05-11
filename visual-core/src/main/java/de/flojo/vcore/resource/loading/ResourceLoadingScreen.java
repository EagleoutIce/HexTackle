package de.flojo.vcore.resource.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.flojo.engine.IAmGameCore;
import dummies.screens.RgbCycleScreen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceLoadingScreen extends ScreenAdapter {

	private final IAmGameCore gameCore;
	private static final int PROGRESS_BAR_HEIGHT = 50;
	private static final int PROGRESS_BAR_WIDTH = 200;
	private final RgbCycleScreen postScreen; // move to game

	public ResourceLoadingScreen(final IAmGameCore gameCore) {
		this.gameCore = gameCore;
		postScreen = new RgbCycleScreen();
	}

	@Override
	public void show() {
		log.info("Showing resource loading screen");
		gameCore.getAssetManager().finishLoading();
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		renderProgressbar();
		if(gameCore.getAssetManager().update()) {
			gameCore.getGame().setScreen(postScreen);
		}
	}

	private void renderProgressbar() {
		final var progress = gameCore.getAssetManager().getProgress();
		final var shapeRenderer = gameCore.getShapeRenderer();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(10, gameCore.getViewport().getWorldHeight() - PROGRESS_BAR_HEIGHT-10f, PROGRESS_BAR_WIDTH*progress, PROGRESS_BAR_HEIGHT);
		shapeRenderer.end();
	}

}
