package com.kaba4cow.futuresscreenerbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	List<Subscriber> findAllSubscribersByState(SubscriberState state);

}
