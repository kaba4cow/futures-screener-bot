package com.kaba4cow.futuresscreenerbot.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormattingUtil {

	private static final DateTimeFormatter dateTimeFormatter;
	private static final DateTimeFormatter dateFormatter;
	private static final DateTimeFormatter timeFormatter;

	private static final DecimalFormat decimalFormat;

	static {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm");
		dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat = new DecimalFormat();
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		decimalFormat.setGroupingUsed(false);
	}

	public static String dateTime(LocalDateTime dateTime) {
		return dateTime.format(dateTimeFormatter);
	}

	public static String dateTime(Long timestamp) {
		return dateTime(TimeConversionUtil.toLocalDateTime(timestamp));
	}

	public static String date(LocalDateTime date) {
		return date.format(dateFormatter);
	}

	public static String time(LocalDateTime time) {
		return time.format(timeFormatter);
	}

	public static String number(double number, int digits) {
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

	public static String number(double number) {
		return number(number, 3);
	}

	public static String percent(double percent) {
		decimalFormat.setMinimumFractionDigits(0);
		decimalFormat.setMaximumFractionDigits(2);
		return String.format("%s%%", decimalFormat.format(100d * percent));
	}

	public static String percentSigned(double percent) {
		if (percent == 0d)
			return percent(0d);
		String sign = percent >= 0d ? "+" : "-";
		return sign + percent(Math.abs(percent));
	}

	public static DecimalFormat getDecimalformat() {
		return decimalFormat;
	}

}
