package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);

        MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary());

        MongoCollection<BsonDocument> coll = db.getCollection("test", BsonDocument.class);
    }
}
