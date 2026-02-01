package cn.starry.hub.database;

import cn.starry.hub.StarryLobby;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

public class MongoDBManager {

    private final StarryLobby plugin;
    private MongoClient mongoClient;
    @Getter
    private MongoDatabase database;

    public MongoDBManager(StarryLobby plugin) {
        this.plugin = plugin;
    }

    public void connect() {
        String uri = plugin.getConfig().getString("mongodb.uri", "mongodb://localhost:27017");
        String dbName = plugin.getConfig().getString("mongodb.database", "starry_lobby");

        try {
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase(dbName);
            plugin.getLogger().info("Successfully connected to MongoDB.");
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to connect to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            plugin.getLogger().info("MongoDB connection closed.");
        }
    }

    public MongoCollection<Document> getCollection(String name) {
        if (database == null) return null;
        return database.getCollection(name);
    }
}
