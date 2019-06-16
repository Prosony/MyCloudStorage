package com.prsn.db;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.prsn.services.ConfigurationProducer;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
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
    private Vector<MongoClient> usedConnections = new Vector<>();
    private Vector<MongoClient> availableConnections = new Vector<>();

    public ConnectionDB() {
//        System.out.println("TITLE: "+producer.getProperty("DataBase.TITLE"));
        POOL_CONNECTION_MAX = 100;
        //Long.parseLong(producer.getProperty("DataBase.POOL_CONNECTION_MAX"));
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
        return MongoClients.create(new ConnectionString(producer.getProperty("DataBase.URI_DEV")));
    }

    public void insertDocument(String titleCollection, Document doc) {
        MongoCollection<Document> collection = retrieve().getDatabase(producer.getProperty("DataBase.TITLE")).getCollection(titleCollection);
        collection.insertOne(doc);
    }


    private static final Logger log = LoggerFactory.getLogger(ConnectionDB.class);

    @Inject
    private ConfigurationProducer producer;

    private final long POOL_CONNECTION_MAX;
}