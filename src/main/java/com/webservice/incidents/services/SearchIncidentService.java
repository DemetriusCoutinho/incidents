package com.webservice.incidents.services;

import com.webservice.incidents.controllers.handler.exceptions.BusinessException;
import com.webservice.incidents.controllers.handler.exceptions.ItemNotFound;
import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.models.IncidentProjection;
import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.repositories.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class SearchIncidentService {
    private final IncidentRepository repository;
    private final Logger logger = LoggerFactory.getLogger(SearchIncidentService.class);

    public SearchIncidentService(
            IncidentRepository repository
    ) {
        this.repository = repository;
    }

    public IncidentResponse findById(String externalId) {
        logger.info("ACTION=findById");
        return repository.findByExternalId(UUID.fromString(externalId))
                .orElseThrow(() -> new ItemNotFound("Item not Found"))
                .fromModel();
    }

    public List<IncidentResponse> findAll() {
        List<IncidentResponse> incidents = repository.findAll()
                .stream()
                .map(Incident::fromModel)
                .toList();

        if (incidents.isEmpty())
            throw new ItemNotFound("Item not Found");

        return incidents;
    }

    public List<IncidentResponse> findLastIncidents(
            Pageable pageable
    ) {
        List<IncidentResponse> incidents = repository.findByLastIncident(pageable.getPageSize())
                .stream()
                .map(Incident::fromModel)
                .toList();

        if (incidents.isEmpty())
            throw new ItemNotFound("Item not Found");

        return incidents;
    }
}
