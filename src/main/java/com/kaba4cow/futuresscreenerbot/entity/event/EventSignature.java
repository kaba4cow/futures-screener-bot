package com.kaba4cow.futuresscreenerbot.entity.event;

import com.kaba4cow.futuresscreenerbot.util.Symbol;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class EventSignature {

	@Enumerated(EnumType.STRING)
	@Column(name = "column_type", updatable = false)
	private EventType type;

	@Embedded
	@AttributeOverrides({ //
			@AttributeOverride(name = "baseAsset", column = @Column(name = "column_base_asset", updatable = false)), //
			@AttributeOverride(name = "quoteAsset", column = @Column(name = "column_quote_asset", updatable = false)) //
	})
	private Symbol symbol;

}
