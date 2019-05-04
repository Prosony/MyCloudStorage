package io.prsn.toolkit.security;

import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;

public interface AuthorizationService {

    /**
	 * Имя шифратора для сервиса.
	 */
	public static final String ENCRYPTOR_NAME = "AuthorizationServiceEncryptor";		
	
	/**
	 * Время жизни для авторизационной информации в секундах.
	 */
	int getTTL();

	/**
	 * Ключ авторизационной информации.
	 */
	String getKey();

	/**
	 * Настройка контекста для авторизированного пользователя.
	 * @param request HTTP запрос.
	 * @param salt Соль.
	 * @param data Данные пользователя.
	 * @param ticketDate Дата выдачи билета.
	 * @return Идентификатор для лога.
	 */
	String setupSecurityContext(ContainerRequestContext request, String salt, String data);
	
	Long getContractIdFromData(String data);
}