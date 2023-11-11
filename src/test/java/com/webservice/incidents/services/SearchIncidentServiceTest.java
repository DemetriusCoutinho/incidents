package com.webservice.incidents.services;

import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.models.IncidentProjection;
import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.repositories.IncidentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchIncidentServiceTest {
    @InjectMocks
    private SearchIncidentService service;
    @Mock
    private IncidentRepository repository;

    @Test
    void findById() {
        when(repository.findByExternalId(any()))
                .thenReturn(Optional.of(
                        new Incident(
                                "incident1",
                                "incidentD1",
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                ));

        IncidentResponse response = service.findById(UUID.randomUUID().toString());

        Assertions.assertNotNull(response);
    }

    @Test
    void findAll() {
        when(repository.findAll())
                .thenReturn(
                        Collections.singletonList(
                                mock(Incident.class)
                        )
                );
        List<IncidentResponse> responses = service.findAll();
        Assertions.assertTrue(Objects.nonNull(responses));
    }

    @Test
    void findLastIncidents() {
        Pageable mockPageable = mock(Pageable.class);

        when(mockPageable.getPageSize()).thenReturn(20);
        when(repository.findByLastIncident(mockPageable.getPageSize()))
                .thenReturn(Collections.singletonList(mock(Incident.class)));

        List<IncidentResponse> incidents = service.findLastIncidents(mockPageable);

        Assertions.assertTrue(Objects.nonNull(incidents));
    }
}