package com.total.motors.store.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionInvalidator implements ApplicationListener<ContextRefreshedEvent> {

    private final SessionRegistry sessionRegistry;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        sessionRegistry.getAllPrincipals().forEach(principal ->
                sessionRegistry.getAllSessions(principal, false).forEach(SessionInformation::expireNow)
        );
        System.out.println("✅ Todas las sesiones han sido invalidadas al reiniciar la aplicación.");
    }
}
