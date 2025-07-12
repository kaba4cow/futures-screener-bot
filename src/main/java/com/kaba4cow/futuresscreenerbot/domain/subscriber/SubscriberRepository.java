package com.kaba4cow.futuresscreenerbot.domain.subscriber;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	List<Subscriber> findAllSubscribersByState(SubscriberState state);

}
