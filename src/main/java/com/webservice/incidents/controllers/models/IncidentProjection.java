package com.webservice.incidents.controllers.models;

import com.webservice.incidents.controllers.responses.IncidentResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IncidentProjection {
    String getName();

    String getDescription();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdateAt();

    LocalDateTime getClosedAt();

    UUID getExternalId();

    default IncidentResponse fromModel() {
        return new IncidentResponse(
                getName(),
                getDescription(),
                getCreatedAt(),
                getUpdateAt(),
                getClosedAt(),
                getExternalId().toString()
        );
    }
}
