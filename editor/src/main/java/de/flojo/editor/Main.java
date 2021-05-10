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

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.io.IOException;
import java.util.Random;

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
            if (newVersionState.equals(NewVersionState.PRESENT) || newVersionState.equals(
                    NewVersionState.SAME_PRESENT)) {
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

    int v = 0;
    long last = System.currentTimeMillis();
    Color lastColor = new Color(0, 0, 0);
    @Override
    public void render() {
        super.render();
        if(System.currentTimeMillis() - last > 14) {
            v = Math.floorMod(v + 1, 256);
            lastColor = new Color(Color.HSBtoRGB(v/255f, .75f, .75f));
        }
        ScreenUtils.clear(lastColor.getRed()/255f, lastColor.getGreen()/255f, lastColor.getBlue()/255f, 1f);
    }
}
