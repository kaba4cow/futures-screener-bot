package com.kaba4cow.futuresscreenerbot.domain.subscriber;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class SubscriberSettings implements SubscriberSettingsProvider {

	@Column(name = "column_pump_threshold")
	private Double pumpThreshold;

	@Column(name = "column_dump_threshold")
	private Double dumpThreshold;

	@Column(name = "column_long_liquidation_threshold")
	private Double longLiquidationThreshold;

	@Column(name = "column_short_liquidation_threshold")
	private Double shortLiquidationThreshold;

}
