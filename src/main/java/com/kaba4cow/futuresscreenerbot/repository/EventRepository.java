package com.kaba4cow.futuresscreenerbot.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import jakarta.transaction.Transactional;

public interface EventRepository extends JpaRepository<Event, UUID> {

	@Query("SELECT COUNT(e) FROM Event e WHERE e.type = :type AND e.symbol = :symbol AND e.eventTime >= :time")
	long countEvents(@Param("type") EventType type, @Param("symbol") Symbol symbol, @Param("time") LocalDateTime time);

	@Modifying
	@Transactional
	@Query("DELETE FROM Event e WHERE e.eventTime < :thresholdTime")
	int deleteExpiredEvents(@Param("thresholdTime") LocalDateTime thresholdTime);

}
