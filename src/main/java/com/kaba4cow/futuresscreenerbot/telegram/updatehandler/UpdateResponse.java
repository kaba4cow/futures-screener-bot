package com.kaba4cow.futuresscreenerbot.telegram.updatehandler;

import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardSupplier;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class UpdateResponse {

	private final String responseText;

	private final ReplyKeyboardSupplier replyKeyboardSupplier;

}
