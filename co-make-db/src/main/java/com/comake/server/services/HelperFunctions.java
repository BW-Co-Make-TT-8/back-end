package com.comake.server.services;

import com.comake.server.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{
    List<ValidationError> getConstraintViolation(Throwable cause);

    boolean isAuthorizedToMakeChange(String username);
}
