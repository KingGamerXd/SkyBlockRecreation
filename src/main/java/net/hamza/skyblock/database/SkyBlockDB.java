package net.hamza.skyblock.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

import java.util.HashMap;

public class SkyBlockDB {

    private static final String DB_URI = "mongodb://localhost";
    private static final String DB_NAME = "SkyBlockRecreation";
    private final HashMap<String , Object> dataCache;
    @Getter
    private static MongoClient client;
    @Getter
    private static MongoDatabase database;
    private MongoCollection<Document> collection;
    private final Document query;
    private Document document;
    @Getter
    private boolean exist = true;


    public SkyBlockDB(String collection ,  Document query){
        this.collection = database.getCollection(collection);
        this.query = query;
        this.dataCache = new HashMap<>();
        init();
    }

    public void setProperty(String string , Object object){
        dataCache.put(string , object);
    }
    private Object getProperty(String key , Object defaultValue){
        if (document != null && document.get(key) != null) return document.get(key);
        return defaultValue;
    }

    public String getString(String key , String defaultValue){
        return getProperty(key , defaultValue).toString();
    }

    public Long getLong(String key , Long defaultValue){
        return Long.parseLong(getString(key , String.valueOf(defaultValue)));
    }
    public int getInteger(String key , Integer defaultValue){
        return (int) getProperty(key , defaultValue);
    }
    public Boolean getBoolean(String key , boolean defaultValue){
        return Boolean.parseBoolean(getProperty(key , defaultValue).toString());
    }
    public Double getDouble(String key , Double defaultValue){
        return Double.parseDouble(getProperty(key , defaultValue).toString());
    }
    private void init(){
        document = collection.find(query).first();
        if (document == null){
            exist = true;
            collection.insertOne(query);
            document = collection.find(query).first();
        }
    }

    public void save(){
        Document updated = collection.find(query).first();
        if (updated == null) return;
        dataCache.forEach(updated::append);
        collection.replaceOne(document , updated);
        document = updated;
    }


    public static void connect(){
        client = MongoClients.create(DB_URI);
        database = client.getDatabase(DB_NAME);
    }

}
