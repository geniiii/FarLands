package site.geni.FarLands.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Config {
	private static ConfigSpec config = new ConfigSpec();

	public static void createConfig(File file) {
		try {
			if (!file.isFile() || !file.getParentFile().isDirectory()) {
				file.getParentFile().mkdirs();
				Files.write(file.toPath(), new GsonBuilder().setPrettyPrinting().create().toJson(config).getBytes(), StandardOpenOption.CREATE_NEW);
			}

			config = new Gson().fromJson(new FileReader(file), ConfigSpec.class);
			Files.write(file.toPath(), new GsonBuilder().setPrettyPrinting().create().toJson(config).getBytes(), StandardOpenOption.WRITE);
		} catch (IOException e) {
			LogManager.getLogger("FarLands").error("[FarLands] Failed to generate config!");
			throw new RuntimeException(e);
		}
	}

	public static ConfigSpec getConfig() {
		return config;
	}

	public static class ConfigSpec {
		public double coordinateScale = 684.4119873046875D;
		public double heightScale = 684.4119873046875D;
		public double coordinateScaleMultiplier = 1;
		public double heightScaleMultiplier = 1;
		public boolean killFallingBlockEntitiesInFarLands = false;
		public boolean farLandsEnabled = true;
	}

}
