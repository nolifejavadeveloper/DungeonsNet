package net.dungeons.generic;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import org.bson.Document;


public class Constants {
    public static InstanceContainer instanceContainer;
    public static GlobalEventHandler eventHandler;
    public static MongoClient mongoClient;
    public static MongoDatabase selectedDatabase;
    public static MongoCollection<Document> playerCollection;
}
