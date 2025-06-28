package com.kaba4cow.futuresscreenerbot.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "column_id")
	private UUID id;

	@Embedded
	@AttributeOverrides({ //
			@AttributeOverride(name = "baseAsset", column = @Column(name = "column_base_asset", updatable = false)), //
			@AttributeOverride(name = "quoteAsset", column = @Column(name = "column_quote_asset", updatable = false)) //
	})
	private Symbol symbol;

	@Column(name = "column_value", updatable = false)
	private Double value;

	@Enumerated(EnumType.STRING)
	@Column(name = "column_type", updatable = false)
	private EventType type;

	@CreationTimestamp
	@Column(name = "column_event_time", updatable = false)
	private LocalDateTime eventTime;

}
