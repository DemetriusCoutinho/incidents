package com.webservice.incidents.repositories;

import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.models.IncidentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findByExternalId(UUID externalId);


    @Query(value =
            " select a from Incident a where a.idIncident in (SELECT inc.idIncident  FROM Incident inc order by inc.idIncident desc limit :size ) order by a.idIncident asc"
    )
    List<Incident> findByLastIncident(@Param(value = "size") int size);
}
