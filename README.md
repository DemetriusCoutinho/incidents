# Projeto Incidentes

Project developed to check, create and delete incidents.

## Technologies used

1. Spring Data Jpa
    - H2 Database
2. Spring Security
    - Basic in-memory authentication
3. Mockito
    - For the construction of unit tests
4. Spring Doc
    - Swagger for API documentation

#### CommandLineStartup

    - An initial load of incidents was added to carry out the tests.

Example:

```java
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
```

## How to execute the project

    - JDK 17
    - Maven
    - Docker

1. build.sh

        - Running script with docker-compose     
   Content:
   ```shell
   echo '------------- Build Project ----------------'
   mvn install package
   echo '------------ Up with Docker Compose --------------'
   docker-compose up -d --build
   ```
2. Users for Access
    1. Admin Access
        - username: admin
        - password: password
    2. User Access
        - username: user
        - password: password

## Project composition

- Documentation Link **{host}/swagger**

    1. Create an incident
        - endpoint: POST {host}/v1/incidents
        - body: {"name":"Incidente de Carga de Banco", "description":"Api A Sobrecarregando o Database"}
        - Autorization: Basic Auth

    2. Update an incident
        - endpoint: PUT {host}/v1/incidents/id/{externalId}
        - body: {"name":"Novo Incidente", "description":"Nova Descrição"}
        - Autorization: Basic Auth

    3. Delete an incident
        - endpoint: DELETE {host}/v1/incidents/id/{externalId}
        - Autorization: Basic Auth
        - Roles: ROLE_ADMIN

    4. List All Incidents
        - endpoint: GET {host}/v1/search/incidents
        - Autorization: Basic Auth

    5. Search for an Incident
        - endpoint: GET {host}/v1/search/incidents/id/{externalId}
        - Autorization: Basic Auth

    6. Search the latest incidents
        - endpoint: GET {host}/v1/search/incidents/last
        - Autorization: Basic Auth 
