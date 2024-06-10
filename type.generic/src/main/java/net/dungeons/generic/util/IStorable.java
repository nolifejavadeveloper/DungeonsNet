package net.dungeons.generic.util;

import org.bson.Document;

public interface IStorable {
    void save(Document doc);
    void load(Document doc);
}
