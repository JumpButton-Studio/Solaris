package net.matthewauld.solaris.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Log {
	private static final String		ANSI_CYAN	= "\u001B[36m";
	private static final String		ANSI_GREEN	= "\u001B[32m";
	private static final String		ANSI_RED	= "\u001B[31m";
	private static final String		ANSI_RESET	= "\u001B[0m";
	private static final String		ANSI_YELLOW	= "\u001B[33m";
	private static int				mode		= 2;
	private static StringBuilder	sb			= new StringBuilder();
	private static long				startTime	= -1;
	// Modes 4 = trace, 3 = debug, 2 = info, 1 = warning, 0 = error
	private static final int		TRACE		= 4, DEBUG = 3, INFO = 2, WARN = 1, ERROR = 0;

	public static void debug(String message) {
		if (mode >= DEBUG) {
			String outTxt = "[" + getTimestamp() + "] " + "DEBUG" + ": " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_GREEN + "DEBUG" + ANSI_RESET + ": " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void debug(String category, String message) {
		if (mode >= DEBUG) {
			String outTxt = "[" + getTimestamp() + "] " + "DEBUG" + ": (" + category + ") " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_GREEN + "DEBUG" + ANSI_RESET + ": (" + category + ") " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void DEBUG() {
		mode = DEBUG;
	}

	public static void dumpToFile(File f) throws IOException {
		FileOutputStream fo = new FileOutputStream(f);
		String output = sb.toString();
		sb = new StringBuilder();

		fo.write(output.getBytes(), 0, output.length());
		fo.close();
		System.out.println(f.getAbsolutePath());
	}

	public static void error(String message) {
		if (mode >= ERROR) {
			String outTxt = "[" + getTimestamp() + "] " + "ERROR" + ": " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_RED + "ERROR" + ANSI_RESET + ": " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void error(String category, String message) {
		if (mode >= ERROR) {
			String outTxt = "[" + getTimestamp() + "] " + "ERROR" + ": (" + category + ") " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_RED + "ERROR" + ANSI_RESET + ": (" + category + ") " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void ERROR() {
		mode = ERROR;
	}

	private static String getTimestamp() {
		if (startTime == -1) {
			startTime = System.currentTimeMillis();
		}

		long uptime = (System.currentTimeMillis() - startTime);
		long days = TimeUnit.MILLISECONDS.toDays(uptime);
		uptime -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(uptime);
		uptime -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
		uptime -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
		uptime -= TimeUnit.MINUTES.toMillis(seconds);

		return (days >= 1 ? days + ":" : "") + (hours <= 9 ? "0" + hours : hours) + ":" + (minutes <= 9 ? "0" + minutes : minutes) + ":" + (seconds <= 9 ? "0" + seconds : seconds);
	}

	public static void info(String message) {
		if (mode >= INFO) {
			String outTxt = "[" + getTimestamp() + "] " + " INFO" + ": " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "]  INFO" + ANSI_RESET + ": " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void info(String category, String message) {
		if (mode >= INFO) {
			String outTxt = "[" + getTimestamp() + "] " + " INFO" + ": (" + category + ") " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "]  INFO" + ANSI_RESET + ": (" + category + ") " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void INFO() {
		mode = INFO;
	}

	public static void trace(String message) {
		if (mode >= TRACE) {
			String outTxt = "[" + getTimestamp() + "] " + "TRACE" + ": " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_CYAN + "TRACE" + ANSI_RESET + ": " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void trace(String category, String message) {
		if (mode >= TRACE) {
			String outTxt = "[" + getTimestamp() + "] " + "TRACE" + ": (" + category + ") " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_CYAN + "TRACE" + ANSI_RESET + ": (" + category + ") " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void TRACE() {
		mode = TRACE;
	}

	public static void warn(String message) {
		if (mode >= WARN) {
			String outTxt = "[" + getTimestamp() + "] " + " WARN" + ": " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_YELLOW + " WARN" + ANSI_RESET + ": " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void warn(String category, String message) {
		if (mode >= WARN) {
			String outTxt = "[" + getTimestamp() + "] " + " WARN" + ": (" + category + ") " + message;
			String txt = ANSI_RESET + "[" + getTimestamp() + "] " + ANSI_YELLOW + " WARN" + ANSI_RESET + ": (" + category + ") " + message;
			sb.append(outTxt + "\n\r");
			System.out.println(txt);
		}
	}

	public static void WARN() {
		mode = WARN;
	}
}
