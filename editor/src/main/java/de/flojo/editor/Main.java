package de.flojo.editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.ScreenUtils;
import de.flojo.core.os.StartGuards;
import de.flojo.core.update.AbstractAutoUpdater;
import de.flojo.core.update.AutoUpdaterFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main extends ApplicationAdapter {

    public static void main(String[] args) {
        if (StartGuards.guardMacOs(args))
            return;
        AbstractAutoUpdater updater = new AutoUpdaterFactory().create();
        final var config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("HexTackle - Editor");
        log.info("Starting editor ({})", config);
        new Lwjgl3Application(new Main(), config);
    }

    @Override
    public void render() {
        super.render();
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }
}
