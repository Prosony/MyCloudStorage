package com.prsn.domain.db;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.prsn.toolkits.config.ConfigurationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Vector;

/**
 * @author プロソニーPRSN
 */

@ApplicationScoped
public class ConnectionDB {

    /**
     * Pool connection to db
     */
    private final Vector<MongoClient> usedConnections = new Vector<>();
    private final Vector<MongoClient> availableConnections = new Vector<>();


    @PostConstruct
    private void init() {
        log.info("TITLE: {}",producer.getConfiguration().getProperty("DataBase.TITLE"));
        POOL_CONNECTION_MAX = Long.parseLong(String.valueOf(producer.getConfiguration().getProperty("DataBase.POOL_CONNECTION_MAX"))); //todo rewrite this shit
    }


    public synchronized MongoClient retrieve() {
        MongoClient client = null;
        if (availableConnections.size() == 0) {
            if ((availableConnections.size()+usedConnections.size()) < POOL_CONNECTION_MAX){
                client = createConnection();
            } else {
                log.error("[ConnectionDB] Maximum pool size reached, no available connections!");
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        } else {
            client = availableConnections.lastElement();
            availableConnections.removeElement(client);
        }
        usedConnections.addElement(client);
        return client;
    }

    public synchronized void putback(MongoClient client) throws RuntimeException {
        if (client != null) {
            if (usedConnections.removeElement(client)) {
                availableConnections.addElement(client);
            } else {
                throw new RuntimeException("Connection not in the usedConnections");
            }
        }
    }

    public void getAvailableConnections() {
        log.info("available connections: [{}], used connections: [{}]",availableConnections.size(),usedConnections.size());
    }

    private MongoClient createConnection() {
        return MongoClients.create(new ConnectionString(String.valueOf(producer.getConfiguration().getProperty("DataBase.URI_DEV"))));
    }



    private static final Logger log = LoggerFactory.getLogger(ConnectionDB.class);

    @Inject
    private ConfigurationProducer producer;

    private long POOL_CONNECTION_MAX;
}