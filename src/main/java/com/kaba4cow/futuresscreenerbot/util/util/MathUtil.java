package com.kaba4cow.futuresscreenerbot.util.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtil {

	public static double map(double x, double srcMin, double srcMax, double destMin, double destMax) {
		return destMin + (destMax - destMin) * (x - srcMin) / (srcMax - srcMin);
	}

	public static double calculateDelta(double oldValue, double newValue) {
		return 100.0 * (newValue - oldValue) / oldValue;
	}

}
