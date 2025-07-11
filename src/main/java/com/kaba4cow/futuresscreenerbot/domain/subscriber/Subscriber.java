package com.kaba4cow.futuresscreenerbot.domain.subscriber;

import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "table_subscriber")
public class Subscriber {

	@Id
	@Column(name = "column_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "column_state")
	private SubscriberState state;

	@Enumerated(EnumType.STRING)
	@Column(name = "column_last_command")
	private Command lastCommand;

	@Embedded
	private SubscriberSettings settings;

}
