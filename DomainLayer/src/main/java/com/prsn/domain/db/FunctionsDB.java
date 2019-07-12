package com.prsn.domain.db;

import com.mongodb.client.MongoCollection;
import com.prsn.toolkits.config.ConfigurationProducer;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


/**
 * @author プロソニーPRSN
 */
@Dependent
public class FunctionsDB {

    public FunctionsDB() {}

    @PostConstruct
    public void init(){
        titleDB = String.valueOf(producer.getConfiguration().getProperty("DataBase.TITLE"));
    }

    public void insertDocument(String titleCollection, Document doc) {
        MongoCollection<Document> collection = connection.retrieve().getDatabase(titleDB).getCollection(titleCollection);
        collection.insertOne(doc);
    }

    public MongoCollection<Document> getCollection(String titleDocument) {
        return connection.retrieve().getDatabase(titleDB).getCollection(titleDocument);
    }


    @Inject
    private ConnectionDB connection ;

    @Inject
    private ConfigurationProducer producer;

    private String titleDB;
    private static final Logger log = LoggerFactory.getLogger(FunctionsDB.class);


}
