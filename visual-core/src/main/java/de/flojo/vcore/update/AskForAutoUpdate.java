package de.flojo.vcore.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.flojo.core.update.AbstractAutoUpdater;
import de.flojo.core.update.AutoUpdaterFactory;
import de.flojo.core.update.NewVersionState;
import de.flojo.engine.dialogs.HTGenericDialog;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class AskForAutoUpdate {
	private final AbstractAutoUpdater updater;
	private final Stage stage;
	private final HTGenericDialog dialog;

	public AskForAutoUpdate(final Stage stage) {
		this.stage = stage;
		dialog = new HTGenericDialog("Update");
		setupDialog();
		updater = new AutoUpdaterFactory().create();
		updater.registerNewVersionListener(this::onNewVersionState);
	}

	private void setupDialog() {
		dialog.text("There is an update available!") // TODO: show version
			  .setYesButton("Download", new InputListener() {
				  @Override
				  public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer,
										   final int button) {
					  downloadUpdate();
					  return true;
				  }
			  })
			  .setNoButton("No", new InputListener() {
			  });
	}

	public void fetch() {
		updater.fetch();
	}

	public AbstractAutoUpdater getUpdater() {
		return updater;
	}

	private void onNewVersionState(final NewVersionState newVersionState) {
		// warning: potential downgrade for same!
		if (newVersionState.equals(NewVersionState.PRESENT))
			askForUpdate();
	}

	private void askForUpdate() {
		dialog.show(stage);
	}

	private void downloadUpdate() {
		dialog.hide();
		try {
			var downloader = updater.getDownloader(true);
			log.info("Update to: {}", AutoUpdaterFactory.JAR_PATH);
			// this should be buffered/changed too
			downloader.downloadTo(Path.of(AutoUpdaterFactory.JAR_PATH));
			// TODO: start new and then delete old
			Gdx.app.exit();
		} catch (IOException e) {
			log.error("Unable to retrieve downloader new Jar. ", e);
		}
	}
}
