package site.geni.FarLands.util;

import io.github.cottonmc.cotton.config.annotations.ConfigFile;
import io.github.cottonmc.repackage.blue.endless.jankson.Comment;

@ConfigFile(name = "FarLands")
public class Config {
	@Comment("The world's coordinate scale")
	public double coordinateScale = 684.4119873046875D;
	@Comment("The world's height scale")
	public double heightScale = 684.4119873046875D;
	@Comment("The coordinate scale multiplier (coordinate scale * multiplier)")
	public double coordinateScaleMultiplier = 1;
	@Comment("The height scale multiplier (height scale * multiplier)")
	public double heightScaleMultiplier = 1;
	@Comment("Whether or not falling block entities (such as sand, gravel, etc...) should be killed when spawned in the Far Lands")
	public boolean killFallingBlockEntitiesInFarLands = false;
	@Comment("Whether or not the Far Lands should generate")
	public boolean farLandsEnabled = true;
	@Comment("Fixes precision loss with ore generation")
	public boolean fixOreGeneration = true;
	@Comment("Fixes precision loss with some particles and entities")
	public boolean fixParticlesEntities = true;
	@Comment("Fixes lighting up to X/Z: ±2^26, causes issues with existing worlds and multiplayer servers")
	public boolean fixLighting = false;
	@Comment("Fixes mob spawning, however mobs still don't spawn past the default world border")
	public boolean fixMobSpawning = false;
}
