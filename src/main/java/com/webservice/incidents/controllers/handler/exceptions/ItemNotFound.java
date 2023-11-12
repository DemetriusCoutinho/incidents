package com.webservice.incidents.controllers.handler.exceptions;

public class ItemNotFound extends BusinessException {
    public ItemNotFound(String message) {
        super(message);
    }
}
