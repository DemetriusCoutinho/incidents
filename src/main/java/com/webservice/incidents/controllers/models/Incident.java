package com.webservice.incidents.controllers.models;

import com.webservice.incidents.controllers.responses.IncidentResponse;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "INCIDENTTB")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idIncident;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime closedAt;
    private UUID externalId;

    public Incident(
            String name,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updateAt,
            LocalDateTime closedAt
    ) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.closedAt = closedAt;
        externalId = UUID.randomUUID();
    }

    public Incident() {
    }

    public IncidentResponse fromModel() {
        return new IncidentResponse(
                name,
                description,
                createdAt,
                updateAt,
                closedAt,
                externalId.toString()
        );
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdateAt() {
        this.updateAt = LocalDateTime.now();
    }

    public void updateClosedAt() {
        this.closedAt = LocalDateTime.now();
    }
}
