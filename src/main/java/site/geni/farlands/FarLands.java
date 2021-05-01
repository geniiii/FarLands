package site.geni.farlands;

import io.github.cottonmc.cotton.logging.ModLogger;
import me.zeroeightsix.fiber.exception.FiberException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.server.command.CommandManager;
import site.geni.farlands.config.Config;
import site.geni.farlands.gui.CustomizeFarLandsScreen;

@SuppressWarnings("unused")
public class FarLands implements ModInitializer {
    private static Config CONFIG;

    static {
        try {
            CONFIG = new Config().load();
        } catch (FiberException e) {
            e.printStackTrace();
        }
    }

    private static final ModLogger LOGGER = new ModLogger(FarLands.class);

    public static Config getConfig() {
        return CONFIG;
    }

    public static void saveConfig() {
        CONFIG.save();
    }

    public static ModLogger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("[FarLands] Initialized");
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> dispatcher.register(CommandManager.literal("farlands").executes(source -> {
			CustomizeFarLandsScreen.createAndOpenConfigScreen(MinecraftClient.getInstance().currentScreen instanceof ChatScreen ? null : MinecraftClient.getInstance().currentScreen);
			return 1;
		}))));
    }
}
