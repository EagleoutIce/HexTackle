package de.flojo.editor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.flojo.engine.IAmGameCore;
import de.flojo.vcore.resource.loading.LoadingScreen;


public class Main extends Game implements IAmGameCore {
	private AssetManager assetManager;
	private BitmapFont font;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Viewport viewport;

	@Override
	public void resize(final int width, final int height) {
		viewport.update(width, height, true);
		shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		assetManager = new AssetManager();
		shapeRenderer = new ShapeRenderer();
		viewport = new StretchViewport(800, 600);
		setScreen(new LoadingScreen(this));
	}

	// todo: sage and label; render correctly

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();
		/*
		if (System.currentTimeMillis() - last > 25) {
			v = Math.floorMod(v + 1, 256);
			lastColor = new Color(Color.HSBtoRGB(v / 255f, .75f, .75f));
			last = System.currentTimeMillis();
		}
		ScreenUtils.clear(lastColor.getRed() / 255f, lastColor.getGreen() / 255f, lastColor.getBlue() / 255f, 1f);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		font.draw(batch, "Update State: " + updater.getUpdater().getNewVersionState(), 10, 15);
		batch.end();
		*/
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
	}

	@Override
	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	@Override
	public Game getGame() {
		return this;
	}

	@Override
	public Viewport getViewport() {
		return viewport;
	}
}
