package com.webservice.incidents.services;

import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.requests.IncidentRequest;
import com.webservice.incidents.controllers.responses.IncidentResponse;
import com.webservice.incidents.repositories.IncidentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    @InjectMocks
    private IncidentService service;
    @Mock
    private IncidentRepository repository;

    @Test
    void create() {
        when(repository.save(any())).thenReturn(
                new Incident(
                        "incident1",
                        "incidentD1",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
        IncidentResponse response = service.create(new IncidentRequest("incident1", "incidentD1"));
        Assertions.assertNotNull(response.externalId());
    }

    @Test
    void update() {
        when(repository.findByExternalId(any())).
                thenReturn(
                        Optional.of(
                                new Incident(
                                        "incident1",
                                        "incidentD1",
                                        LocalDateTime.now(),
                                        LocalDateTime.now(),
                                        LocalDateTime.now()
                                )
                        )
                );
        when(repository.save(any())).thenReturn(
                new Incident(
                        "atualizado",
                        "atualizado",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
        IncidentResponse response = service.update(
                UUID.randomUUID().toString(),
                new IncidentRequest(
                        "update1",
                        "update2"
                )
        );
        Assertions.assertEquals("atualizado", response.name());
    }

    @Test
    void delete() {
        when(repository.findByExternalId(any())).thenReturn(
                Optional.of(
                        new Incident(
                                "incident1",
                                "incidentD1",
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                )
        );
        doNothing().when(repository).delete(any());
        service.delete(UUID.randomUUID().toString());
        verify(repository, times(1)).delete(any());
    }
}