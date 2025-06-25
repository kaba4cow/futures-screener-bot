package com.kaba4cow.futuresscreenerbot.service.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.SubscriberSettingsExtractor;
import com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.SubscriberSettingsExtractorRegistry;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriberService {

	private final SubscriberRepository subscriberRepository;

	private final SubscriberSettingsExtractorRegistry subscriberSettingsExtractorRegistry;

	private final SubscriberSettingsFactory subscriberSettingsFactory;

	private final SubscriberFilter subscriberFilter;

	public Subscriber getOrRegisterSubscriber(Long id) {
		return subscriberRepository.findById(id).orElseGet(() -> registerSubscriber(id));
	}

	private Subscriber registerSubscriber(Long id) {
		Subscriber subscriber = new Subscriber();
		subscriber.setId(id);
		subscriber.setState(SubscriberState.SUBSCRIBED);
		subscriber.setLastCommand(CommandIdentifier.NONE);
		subscriber.setSettings(subscriberSettingsFactory.createSettings());
		Subscriber savedSubscriber = subscriberRepository.save(subscriber);
		log.info("Registered subscriber [id={}]", id);
		return savedSubscriber;
	}

	public List<Subscriber> getSubscribersForEvent(Event event) {
		SubscriberSettingsExtractor extractor = subscriberSettingsExtractorRegistry.getExtractor(event.getType());
		return subscriberRepository.findAllSubscribersByState(SubscriberState.SUBSCRIBED).stream()//
				.filter(subscriber -> subscriberFilter.filter(subscriber, extractor, event))//
				.collect(Collectors.toList());
	}

}
