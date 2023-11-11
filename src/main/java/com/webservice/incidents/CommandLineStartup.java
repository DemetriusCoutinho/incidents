package com.webservice.incidents;

import com.webservice.incidents.controllers.models.Incident;
import com.webservice.incidents.controllers.requests.IncidentRequest;
import com.webservice.incidents.repositories.IncidentRepository;
import com.webservice.incidents.services.IncidentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartup implements CommandLineRunner {
    private final IncidentService service;

    public CommandLineStartup(IncidentService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i <= 100; i++)
            service.create(
                    new IncidentRequest(
                            "inc_" + i,
                            "desc_inc_" + i
                    )
            );
    }
}
