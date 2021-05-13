package de.flojo.editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.flojo.core.logging.ConsumerPrintStreamBridge;
import de.flojo.core.os.StartGuards;
import de.flojo.core.version.IPackageMetaInformation;
import de.flojo.core.version.ht.HtVersionRetriever;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DesktopMain extends ApplicationAdapter {

	public static final IPackageMetaInformation THIS_PACKAGE = HtVersionRetriever.getRetriever().getMeta("editor");

	public static void main(String[] args) {
		if (StartGuards.guardMacOs(args))
			return;

		final var config = new Lwjgl3ApplicationConfiguration();
		log.debug("Setup configuration");
		config.setTitle("HexTackle - Editor (" + THIS_PACKAGE.getVersion().getVersionString() + ")");
		config.enableGLDebugOutput(true, new ConsumerPrintStreamBridge(log::debug));
		log.info("Starting editor ({})", config);
		new Lwjgl3Application(new Main(), config);
	}

}
