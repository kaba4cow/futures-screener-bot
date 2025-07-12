package com.kaba4cow.futuresscreenerbot.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface EventRepository extends JpaRepository<Event, UUID> {

	@Query("SELECT COUNT(e) FROM Event e WHERE e.signature = :signature AND e.eventTime >= :time")
	long countEvents(@Param("signature") EventSignature signature, @Param("time") LocalDateTime time);

	@Modifying
	@Transactional
	@Query("DELETE FROM Event e WHERE e.eventTime < :time")
	int deleteEventsBefore(@Param("time") LocalDateTime time);

}
