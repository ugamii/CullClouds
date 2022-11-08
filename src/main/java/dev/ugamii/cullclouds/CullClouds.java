package dev.ugamii.cullclouds;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CullClouds implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("cullclouds");

	@Override
	public void onInitializeClient() {
		LOGGER.info("[Cull Clouds] this mod doesnt cull clouds");
	}
}
