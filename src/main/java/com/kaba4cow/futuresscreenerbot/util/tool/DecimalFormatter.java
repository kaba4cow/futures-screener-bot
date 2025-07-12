package com.kaba4cow.futuresscreenerbot.util.tool;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DecimalFormatter {

	private static final double THOUSANDS = 1.0e+3;
	private static final double MILLIONS = 1.0e+6;
	private static final double BILLIONS = 1.0e+9;
	private static final double TRILLIONS = 1.0e+12;

	public static String formatWithSuffix(double number, int digits) {
		String suffix = "";
		if (number > TRILLIONS) {
			number /= TRILLIONS;
			suffix = "T";
		} else if (number > BILLIONS) {
			number /= BILLIONS;
			suffix = "B";
		} else if (number > MILLIONS) {
			number /= MILLIONS;
			suffix = "M";
		} else if (number > THOUSANDS) {
			number /= THOUSANDS;
			suffix = "k";
		}
		return format(number, digits).concat(suffix);
	}

	public static String format(double number, int digits) {
		return createFormat(digits).format(number);
	}

	private static DecimalFormat createFormat(int digits) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat();
		format.setDecimalFormatSymbols(symbols);
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(digits);
		return format;
	}

}
