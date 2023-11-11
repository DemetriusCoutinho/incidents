package com.webservice.incidents.controllers.requests;

import java.time.LocalDateTime;

public record IncidentUpdateRequest(String name,
                                    String description
) {
}
