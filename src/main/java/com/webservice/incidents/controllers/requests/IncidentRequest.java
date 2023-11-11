package com.webservice.incidents.controllers.requests;

import com.webservice.incidents.controllers.models.Incident;

import java.time.LocalDateTime;

public record IncidentRequest(String name,
                              String description
) {

    public Incident fromRequest() {
        return new Incident(
                name,
                description,
                LocalDateTime.now(),
                null,
                null
        );
    }

}
