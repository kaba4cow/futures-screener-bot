package com.kaba4cow.futuresscreenerbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberState;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	List<Subscriber> findAllSubscribersByState(SubscriberState state);

}
