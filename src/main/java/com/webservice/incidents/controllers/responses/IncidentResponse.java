package com.webservice.incidents.controllers.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record IncidentResponse(
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updateAt,
        LocalDateTime closedAt,
        String externalId
) {
}
