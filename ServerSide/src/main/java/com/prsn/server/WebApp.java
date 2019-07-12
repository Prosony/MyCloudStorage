package com.prsn.server;

import com.prsn.toolkits.AppMode;
import com.prsn.toolkits.config.ConfigurationProducer;
import com.prsn.toolkits.config.annotation.ConfiguredValue;
import com.prsn.toolkits.security.AuthorizationService;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jboss.resteasy.cdi.ResteasyCdiExtension;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * @author プロソニーPRSN
 */
@ApplicationScoped
@ApplicationPath("/")
public class WebApp extends Application {

    /**
     * name of the environment variable with the password to encrypt the authentication data.
     */
    public static final String AUTH_PASSWORD_IN_ENV = "MAIN_PASSWORD";


    @SuppressWarnings("unchecked")
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> result = new HashSet<>();
        result.add(WebAppFeatures.class);
        result.addAll((Collection<? extends Class<?>>) (Object)extension.getResources());
        return result;
    }
    /**
     * initialize WebApp.
     */
    @PostConstruct
    public void initialize() {

    }
    /**
     * Servlet for Resteasy.
     */
    @ApplicationScoped
    @WebServlet(urlPatterns={ "/*" }, asyncSupported=true, initParams={
            @WebInitParam(name="javax.ws.rs.Application", value="com.prsn.server.WebApp")
    }, loadOnStartup=1)
    public static class ResteasyServlet extends HttpServlet30Dispatcher {

        private static final long serialVersionUID = 1L;
    }


    /**
     * @return PBEStringEncryptor
     */
    @Produces
    @ApplicationScoped
    @Named(AuthorizationService.ENCRYPTOR_NAME)
    public PBEStringEncryptor getPBEStringEncryptor() {
        // Настройка шифратора для аутентификационных данных.
        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(producer.getConfiguration().getString(com.prsn.server.Parameters.AUTH_TICKET_ALGORITHM, com.prsn.server.Parameters.AUTH_TICKET_ALGORITHM_DEFAULT));
        // http://www.jasypt.org/advancedconfiguration.html

        if(appMode.get() == AppMode.PRODUCTION) {
            final EnvironmentStringPBEConfig pbeConfig = new EnvironmentStringPBEConfig();
            pbeConfig.setPasswordEnvName(AUTH_PASSWORD_IN_ENV);
            encryptor.setConfig(pbeConfig);
        } else {
            final SimpleStringPBEConfig pbeConfig = new SimpleStringPBEConfig();
            pbeConfig.setPassword("mAyhVEIkvM");
            encryptor.setConfig(pbeConfig);
        }
        return encryptor;
    }

    @Inject
    private ResteasyCdiExtension extension;

    @Inject
    private ConfigurationProducer producer;

    @Context
    private ServletContext servletContext;

    @Inject
    @ConfiguredValue(
            value = com.prsn.domain.Parameters.APP_MODE,
            def = com.prsn.domain.Parameters.APP_MODE_DEFAULT)
    private Provider<AppMode> appMode;

}
