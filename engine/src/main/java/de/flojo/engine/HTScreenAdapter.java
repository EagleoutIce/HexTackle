package de.flojo.engine;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class HTScreenAdapter extends ScreenAdapter {
	protected final Stage stage;
	private final IAmGameCore gameCore;

	protected HTScreenAdapter(final IAmGameCore gameCore) {
		this.gameCore = gameCore;
		this.stage = new Stage(gameCore.getViewport());
	}

	public final IAmGameCore getGameCore() {
		return gameCore;
	}

	@Override
	public final void dispose() {
		stage.dispose();
		onDispose();
	}

	protected void onDispose() {
	}
}
