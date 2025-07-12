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

	private static final DecimalFormat decimalFormat;

	static {
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat = new DecimalFormat();
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		decimalFormat.setGroupingUsed(false);
	}

	public static String formatNumber(double number, int digits) {
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
		decimalFormat.setMinimumFractionDigits(0);
		decimalFormat.setMaximumFractionDigits(digits);
		return String.format("%s%s", decimalFormat.format(number), suffix);
	}

}
