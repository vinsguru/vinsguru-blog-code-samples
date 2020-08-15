package com.vinsguru.springrsocket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketRequest {

    private UUID requestId;
    private TicketStatus status = TicketStatus.TICKET_PENDING;

    public TicketRequest(UUID requestId) {
        this.requestId = requestId;
    }
}
