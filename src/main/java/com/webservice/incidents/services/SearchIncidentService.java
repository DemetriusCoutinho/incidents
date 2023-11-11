package com.webservice.incidents.services;

import com.webservice.incidents.controllers.handler.exceptions.BusinessException;
import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.models.IncidentProjection;
import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.repositories.IncidentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class SearchIncidentService {
    private final IncidentRepository repository;

    public SearchIncidentService(
            IncidentRepository repository
    ) {
        this.repository = repository;
    }

    public IncidentResponse findById(String externalId) {
        return repository.findByExternalId(UUID.fromString(externalId))
                .orElseThrow(() -> new BusinessException("Item not Found"))
                .fromModel();
    }

    public List<IncidentResponse> findAll() {
        List<IncidentResponse> incidents = repository.findAll()
                .stream()
                .map(Incident::fromModel)
                .toList();

        if (incidents.isEmpty())
            throw new BusinessException("Item not Found");

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
            throw new BusinessException("Item not Found");

        return incidents;
    }
}
