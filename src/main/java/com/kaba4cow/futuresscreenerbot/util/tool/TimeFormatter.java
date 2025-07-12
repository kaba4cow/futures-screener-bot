package com.kaba4cow.futuresscreenerbot.util.tool;

import java.util.concurrent.TimeUnit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeFormatter {

	public static String format(long timeMillis, TimeUnit timeUnit) {
		return String.format("%s %s", DecimalFormatter.format(convert(timeMillis, timeUnit), 3),
				timeUnit.toString().toLowerCase());
	}

	private static double convert(double time, TimeUnit timeUnit) {
		return time / getScale(timeUnit);
	}

	private static double getScale(TimeUnit timeUnit) {
		return switch (timeUnit) {
			case NANOSECONDS -> 1.0;
			case MICROSECONDS -> 1.0e+3;
			case MILLISECONDS -> 1.0e+6;
			case SECONDS -> 1.0e+9;
			case MINUTES -> 60.0 * 1.0e+9;
			case HOURS -> 60.0 * 60.0 * 1.0e+9;
			case DAYS -> 24.0 * 60.0 * 60.0 * 1.0e+9;
			default -> throw new IllegalArgumentException(String.format("Unsupported TimeUnit: %s", timeUnit));
		};
	}

}
