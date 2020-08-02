package com.vinsguru.springrsocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TicketRequest {

    private UUID requestId;
    private TicketStatus status = TicketStatus.TICKET_PENDING;

}
