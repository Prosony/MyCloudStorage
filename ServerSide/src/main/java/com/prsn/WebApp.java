package com.prsn;

import org.jboss.resteasy.cdi.ResteasyCdiExtension;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("/")
public class WebApp extends Application {

    @SuppressWarnings("unchecked")
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> result = new HashSet<>();
        result.add(WebAppFeatures.class);
        result.addAll((Collection<? extends Class<?>>) (Object)extension.getResources());
        return result;
    }
    /**
     * Инициализация web-приложения.
     */
    @PostConstruct
    public void initialize() {

    }
    /**
     * Сервлет для работы Resteasy.
     */
    @ApplicationScoped
    @WebServlet(urlPatterns={ "/*" }, asyncSupported=true, initParams={
            @WebInitParam(name="javax.ws.rs.Application", value="com.prsn.WebApp")
    }, loadOnStartup=1)
    public static class ResteasyServlet extends HttpServlet30Dispatcher {

        private static final long serialVersionUID = 1L;
    }

    @Inject
    private ResteasyCdiExtension extension;
    @Context
    private ServletContext servletContext;


//    private Set<Object> singletonSet = new HashSet<Object>();
//
//    public WebApp() {
//        singletonSet.add(new HelloResources());
//    }
//
//    @Override
//    public Set<Object> getSingletons() {
//        return singletonSet;
//    }
}
