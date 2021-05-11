package de.flojo.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public interface IAmGameCore {
	AssetManager getAssetManager();
	ShapeRenderer getShapeRenderer();
	Game getGame();
	Viewport getViewport();
}
