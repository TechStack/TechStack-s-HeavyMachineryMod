package com.projectreddog.machinemod.utility;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.projectreddog.machinemod.reference.Reference;

public class LogHelper {

	public static void log(Level logLevel, Object object) {
		// TODO if perf issue CACHE the logger
		LogManager.getLogger(Reference.MOD_NAME).log(logLevel, String.valueOf(object));

	}

	public static void info(Object ojbect) {
		log(Level.INFO, ojbect);
	}

	public static void all(Object ojbect) {
		log(Level.ALL, ojbect);
	}

	public static void debug(Object ojbect) {
		log(Level.DEBUG, ojbect);
	}

	public static void fatal(Object ojbect) {
		log(Level.FATAL, ojbect);
	}

	public static void error(Object ojbect) {
		log(Level.ERROR, ojbect);
	}

	public static void warn(Object ojbect) {
		log(Level.WARN, ojbect);
	}

	public static void off(Object ojbect) {
		log(Level.OFF, ojbect);
	}

	public static void trace(Object ojbect) {
		log(Level.TRACE, ojbect);
	}

}
