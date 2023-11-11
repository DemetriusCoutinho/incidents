package com.webservice.incidents.controllers.handler.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(String message,
                       String messageDetails
) {

}
