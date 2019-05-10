package com.prsn.security;


import javax.ws.rs.container.ContainerRequestContext;

public interface AuthenticationService{
    /**
     * Аутентификация.
     * @param context Контекст запроса.
     * @param phone Лолин пользователя.
     * @param code пароль пользователя.
     */
    void authenticate(ContainerRequestContext context, String phone, String code);
}