package com.kaba4cow.futuresscreenerbot.util.util;

import org.json.JSONArray;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {

	public static boolean contains(JSONArray array, String string) {
		for (int index = 0; index < array.length(); index++)
			if (array.getString(index).equals(string))
				return true;
		return false;
	}

}
