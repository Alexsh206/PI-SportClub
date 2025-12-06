package com.sportclub.repository;

import com.sportclub.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByEventId(Long eventId);

    List<Payment> findBySpectatorId(Long spectatorId);
}
