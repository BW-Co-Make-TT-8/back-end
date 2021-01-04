package com.comake.server.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor()
    {
        String username;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            username = auth.getName();
        } else
        {
            username = "SYSTEM";
        }
        return Optional.of(username);
    }
}
