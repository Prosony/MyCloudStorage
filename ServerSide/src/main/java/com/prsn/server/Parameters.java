package com.prsn.server;

public final class Parameters {
    /**
     * Алгоритм для шифрования аутентификационного билета.
     */
    public static final String AUTH_TICKET_ALGORITHM = "web.authAlgorithm";
    /**
     * Алгоритм для шифрования аутентификационного билета, значение по-умолчанию.
     */
    public static final String AUTH_TICKET_ALGORITHM_DEFAULT = "PBEWITHSHA1ANDDESEDE";

}
