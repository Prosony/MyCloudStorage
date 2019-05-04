package io.prsn.toolkit.security;

import javax.ws.rs.container.ContainerRequestContext;

public interface AuthenticationService{
    /**
	 * Аутентификация.
	 * @param context Контекст запроса.
	 * @param login Лолин пользователя.
	 * @param password пароль пользователя.
	 */
	void authenticate(ContainerRequestContext context, String phone, String code);
}