package net.dungeons.generic;

import com.mongodb.client.MongoClient;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;


public class Constants {
    public static InstanceContainer instanceContainer;
    public static GlobalEventHandler eventHandler;
    public static MongoClient mongoClient;
}
