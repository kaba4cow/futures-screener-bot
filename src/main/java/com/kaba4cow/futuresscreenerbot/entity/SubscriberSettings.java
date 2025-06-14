package com.kaba4cow.futuresscreenerbot.entity;

import java.math.BigDecimal;

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
public class SubscriberSettings {

	@Column(name = "column_pump_threshold")
	private BigDecimal pumpThreshold;

	@Column(name = "column_dump_threshold")
	private BigDecimal dumpThreshold;

	@Column(name = "column_long_liquidation_threshold")
	private BigDecimal longLiquidationThreshold;

	@Column(name = "column_short_liquidation_threshold")
	private BigDecimal shortLiquidationThreshold;

}
