package com.webservice.incidents.controllers;

import com.webservice.incidents.controllers.requests.IncidentRequest;
import com.webservice.incidents.controllers.requests.IncidentUpdateRequest;
import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.services.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/incidents")
public class IncidentController {
    private final IncidentService service;

    public IncidentController(
            IncidentService service
    ) {
        this.service = service;
    }

    @PostMapping
    public IncidentResponse create(@RequestBody IncidentRequest incidentRequest) {
        return service.create(incidentRequest);
    }

    @PutMapping("/id/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public IncidentResponse update(
            @PathVariable String externalId,
            @RequestBody IncidentRequest updateRequest
    ) {
        return service.update(externalId, updateRequest);
    }

    @DeleteMapping("/id/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String externalId) {
        service.delete(externalId);
    }
}
