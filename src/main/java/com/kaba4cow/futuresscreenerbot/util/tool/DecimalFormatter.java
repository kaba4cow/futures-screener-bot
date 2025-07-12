package com.kaba4cow.futuresscreenerbot.util.tool;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DecimalFormatter {

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
		if (number > 1e+12d) {
			number /= 1e+12d;
			suffix = "T";
		} else if (number > 1e+9d) {
			number /= 1e+9d;
			suffix = "B";
		} else if (number > 1e+6d) {
			number /= 1e+6d;
			suffix = "M";
		}
		decimalFormat.setMinimumFractionDigits(0);
		decimalFormat.setMaximumFractionDigits(digits);
		return String.format("%s%s", decimalFormat.format(number), suffix);
	}

}
