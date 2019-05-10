package com.prsn;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Настройка web-приложения.
 */
@Dependent
public class WebAppFeatures implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        return true;
    }
}