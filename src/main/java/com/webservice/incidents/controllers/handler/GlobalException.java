package com.webservice.incidents.controllers.handler;

import com.webservice.incidents.controllers.handler.exceptions.BusinessException;
import com.webservice.incidents.controllers.handler.exceptions.ItemNotFound;
import com.webservice.incidents.controllers.handler.responses.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleExceptionInternal(
            BusinessException ex,
            WebRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ApiError(
                                "Ocorreu um error Interno",
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(ItemNotFound.class)
    public ResponseEntity<Object> handleExceptionInternal(
            ItemNotFound ex,
            WebRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiError(
                                "Ocorreu um error Interno",
                                ex.getMessage()
                        )
                );
    }
}
