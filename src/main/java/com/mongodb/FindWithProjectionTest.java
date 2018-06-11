package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.util.Helpers.printJson;

public class FindWithProjectionTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

//        Bson projection = new Document("y", 1)
//                .append("i", 1)
//                .append("_id", 0);

//        Bson projection = Projections.exclude("x", "_id");

        Bson projection = fields(include("y", "i"), excludeId());


        List<Document> all = collection
                .find(filter)
                .projection(projection)
                .into(new ArrayList<Document>());

        for (Document cur : all) {
            printJson(cur);
        }
    }

}
