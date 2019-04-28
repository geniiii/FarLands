package site.geni.FarLands.util;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TextFormat;

public class LocationUtil {
	private static final String INVALID = TextFormat.RED + I18n.translate("config.farlands.invalid");

	// Locations
	private static int farLandsLocation = (int) (Integer.MAX_VALUE / ((Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier) / 4));
	private static long fartherLandsLocation = farLandsLocation * 80;
	private static long farthererLandsLocation = (long) (Long.MAX_VALUE / ((Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier) / 4));
	private static long farthestLandsLocation = farthererLandsLocation * 80;


	// Validators
	public static boolean isFarLandsLocationValid() {
		return farLandsLocation >= 0 && farLandsLocation != Integer.MAX_VALUE;
	}

	public static boolean isFartherLandsLocationValid() {
		return fartherLandsLocation >= 0 && LocationUtil.isFarLandsLocationValid();
	}

	public static boolean isFarthererLandsLocationValid() {
		return farthererLandsLocation >= 0 && farthererLandsLocation != Long.MAX_VALUE;
	}

	public static boolean isFarthestLandsLocationValid() {
		return farthestLandsLocation >= 0 && LocationUtil.isFarthererLandsLocationValid();
	}


	// Getters
	public static String getFarlandsLocationString() {
		return LocationUtil.isFarLandsLocationValid() ? "±" + farLandsLocation : INVALID;
	}

	public static String getFartherLandsLocationString() {
		return LocationUtil.isFartherLandsLocationValid() ? "±" + fartherLandsLocation : INVALID;
	}

	public static String getFarthererLandsLocationString() {
		return LocationUtil.isFarthererLandsLocationValid() ? "±" + farthererLandsLocation : INVALID;
	}

	public static String getFarthestLandsLocationString() {
		return LocationUtil.isFarthestLandsLocationValid() ? "±" + farthestLandsLocation : INVALID;
	}


	/**
	 * Updates {@link #farLandsLocation}, {@link #fartherLandsLocation}, {@link #farthererLandsLocation} and {@link #farthestLandsLocation}
	 *
	 * @param config {@link site.geni.FarLands.util.Config.ConfigSpec} to take values from
	 * @author geni
	 */
	public static void updateLocations(Config.ConfigSpec config) {
		farLandsLocation = (int) (Integer.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4));
		fartherLandsLocation = (long) farLandsLocation  * 80;

		farthererLandsLocation = (long) (Long.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4));
		farthestLandsLocation = fartherLandsLocation * 80;
	}
}
