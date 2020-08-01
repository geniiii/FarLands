package io.github.cottonmc.cotton.logging;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLogger {
	private Logger log;

	private String prefix;
	private Ansi prefixFormat = new Ansi(Ansi.SANE);

	public ModLogger(Class clazz) {
		this(clazz.getSimpleName(), clazz.getSimpleName());
	}

	public ModLogger(String name, String prefix) {
		this.log = LogManager.getLogger(name);
		setPrefix(prefix);
	}

	private void setPrefix(String prefix) {
		if (prefix.length() > 0) {
			this.prefix = "[" + prefix + "]: ";
		} else {
			this.prefix = "";
		}
	}

	/**
	 * Use this method if you want to add custom color or formatting to the log prefix.
	 *
	 * @param format Formatting to be used.
	 */
	public void setPrefixFormat(Ansi format) {
		this.prefixFormat = format;
	}


	public void retarget(Logger to) {
		log = to;
	}

	public void log(Level level, String msg, Ansi ansi, Object... data) {
		//since Ansi might not work in a normal windows console we only use it in the dev environemnt
		log.log(level, getFormattedPrefix() + (FabricLoader.getInstance().isDevelopmentEnvironment() ? ansi.format(msg, data) : String.format(msg, data)));
	}

	public void log(Level level, Throwable ex, String msg, Ansi ansi, Object... data) {
		//since Ansi might not work in a normal windows console we only use it in the dev environemnt
		log.log(level, getFormattedPrefix() + (FabricLoader.getInstance().isDevelopmentEnvironment() ? ansi.format(msg, data) : String.format(msg, data)), ex);
	}

	private String getFormattedPrefix() {
		//since Ansi might not work in a normal windows console we only use it in the dev environemnt
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			return this.prefixFormat.format(this.prefix);
		} else {
			return this.prefix;
		}
	}

	public void error(String msg, Object... data) {
		log(Level.ERROR, msg, Ansi.Red.and(Ansi.Bold), data);
	}

	public void warn(String msg, Object... data) {
		log(Level.WARN, msg, Ansi.Red.and(Ansi.Bold), data);
	}

	public void info(String msg, Object... data) {
		log(Level.INFO, msg, Ansi.Sane, data);
	}

	public void infoBig(String msg, Object... data) {
		log(Level.INFO, msg, Ansi.Bold, data);
	}

	public void success(String msg, Object... data) {
		log(Level.INFO, msg, Ansi.Green.and(Ansi.Bold), data);
	}


	public void debug(String msg, Object... data) {
		log(Level.DEBUG, msg, Ansi.Sane, data);
	}

	public void trace(String msg, Object... data) {
		log(Level.TRACE, msg, Ansi.Sane, data);
	}

	public void all(String msg, Object... data) {
		log(Level.TRACE, msg, Ansi.Sane, data);
	}
}
