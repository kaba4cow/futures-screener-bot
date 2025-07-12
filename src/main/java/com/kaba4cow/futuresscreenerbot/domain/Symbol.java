package com.kaba4cow.futuresscreenerbot.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(doNotUseGetters = true)
@Embeddable
public class Symbol {

	private String baseAsset;

	private String quoteAsset;

	public String toSymbolString() {
		return baseAsset.concat(quoteAsset);
	}

	public String toAssetsString() {
		return String.format("%s/%s", baseAsset, quoteAsset);
	}

}
