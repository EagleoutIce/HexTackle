package de.flojo.editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.ScreenUtils;
import de.flojo.core.os.StartGuards;
import de.flojo.core.update.AbstractAutoUpdater;
import de.flojo.core.update.AutoUpdaterFactory;
import de.flojo.core.update.NewVersionState;
import de.flojo.core.update.downloaders.IDownloadUpdate;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Main extends ApplicationAdapter {

    public static void main(String[] args) {
        if (StartGuards.guardMacOs(args)) {
            return;
        }
        AbstractAutoUpdater updater = new AutoUpdaterFactory().create();
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
            if(newVersionState.equals(NewVersionState.PRESENT) || newVersionState.equals(NewVersionState.SAME_PRESENT)) {
                try {
                    IDownloadUpdate downloader = updater.getDownloader(true);
                    log.info("Forcing update to: {}", AutoUpdaterFactory.JAR_PATH);
                    downloader.downloadTo(AutoUpdaterFactory.JAR_PATH); // this should be buffered/changed too
                } catch (IOException e) {
                    log.error("Unable to retrieve downloader new Jar. ", e);
                }
            }
        }));
    }

    @Override
    public void render() {
        super.render();
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }
}
