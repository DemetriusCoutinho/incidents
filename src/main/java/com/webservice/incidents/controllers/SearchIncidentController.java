package com.webservice.incidents.controllers;

import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.services.SearchIncidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search/incidents")
public class SearchIncidentController {
    private final SearchIncidentService service;

    public SearchIncidentController(
            SearchIncidentService service
    ) {
        this.service = service;
    }

    @GetMapping("/id/{externalId}")
    public IncidentResponse findById(@PathVariable String externalId) {
        return service.findById(externalId);
    }

    @GetMapping
    public List<IncidentResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/last")
    public List<IncidentResponse> findLastIncidents(Pageable pageable) {
        return service.findLastIncidents(pageable);
    }

}
