package de.flojo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.ScreenUtils;
import de.flojo.core.StartGuards;

public class Main extends ApplicationAdapter {

    public static void main(String[] args) {
        if(StartGuards.guardMacOs(args))
            return;
        final var config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("123");
        new Lwjgl3Application(new Main(), config);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void render() {
        super.render();
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }
}
