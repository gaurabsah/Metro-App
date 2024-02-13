package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.QRTicket;

public interface QRTicketRepository extends JpaRepository<QRTicket, String> {

}
