package com.kaba4cow.futuresscreenerbot.service.domain;

import java.math.BigDecimal;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.properties.SubscriberSettingsProperties;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriberService {

	private final SubscriberRepository subscriberRepository;

	private final SubscriberSettingsProperties subscriberSettingsProperties;

	public Subscriber getOrRegisterSubscriber(Long id) {
		return subscriberRepository.findById(id).orElseGet(() -> registerSubscriber(id));
	}

	private Subscriber registerSubscriber(Long id) {
		Subscriber subscriber = new Subscriber();
		subscriber.setId(id);
		subscriber.setState(SubscriberState.SUBSCRIBED);
		subscriber.setLastCommand(CommandIdentifier.NONE);
		subscriber.setSettings(createSettings());
		Subscriber savedSubscriber = subscriberRepository.save(subscriber);
		log.info("Registered subscriber [id={}]", id);
		return savedSubscriber;
	}

	private SubscriberSettings createSettings() {
		SubscriberSettings settings = new SubscriberSettings();
		settings.setPumpThreshold(subscriberSettingsProperties.getPumpThreshold());
		settings.setDumpThreshold(subscriberSettingsProperties.getDumpThreshold());
		settings.setLongLiquidationThreshold(subscriberSettingsProperties.getLongLiquidationThreshold());
		settings.setShortLiquidationThreshold(subscriberSettingsProperties.getShortLiquidationThreshold());
		return settings;
	}

	public void updateSubscriberSettings(Subscriber subscriber, BiConsumer<SubscriberSettings, BigDecimal> setter,
			BigDecimal value) {
		setter.accept(subscriber.getSettings(), value);
		subscriberRepository.save(subscriber);
		log.info("Updated subscriber settings [id={}]", subscriber.getId());
	}

}
