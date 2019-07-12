package com.prsn.toolkits;


/**
 * Текущий режим работы.
 */
public enum AppMode {

    /**
     * Внутри другой программы.
     */
    TOOL,

    /**
     * В продуктиве.
     */
    PRODUCTION,

    /**
     * Тестовый режим.
     */
    TESTING,

    /**
     * Разработка.
     */
    DEVELOPMENT
}
