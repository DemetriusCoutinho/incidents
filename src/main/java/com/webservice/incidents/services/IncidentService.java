package com.webservice.incidents.services;

import com.webservice.incidents.controllers.handler.exceptions.BusinessException;
import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.requests.IncidentRequest;
import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.repositories.IncidentRepository;
import com.webservice.incidents.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public IncidentResponse create(IncidentRequest incidentRequest) {
        Incident save = repository.save(
                incidentRequest.fromRequest()
        );
        return save.fromModel();
    }

    public IncidentResponse update(
            String externalId,
            IncidentRequest updateRequest
    ) {
        Incident incident = repository.findByExternalId(UUID.fromString(externalId))
                .orElseThrow(() -> new BusinessException("Incident Not Found"));
        incident.setUpdateAt();
        BeanUtils.copyProperties(updateRequest, incident);
        return repository.save(incident).fromModel();
    }

    public void delete(String externalId) {
        Incident incident = repository.findByExternalId(UUID.fromString(externalId))
                .orElseThrow(() -> new BusinessException("Incident Not Found"));
        repository.delete(incident);
    }
}
