package net.dungeons.generic.player;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.Constants;
import net.dungeons.generic.level.SkyblockLevel;
import net.dungeons.generic.pet.SkyblockPet;
import net.dungeons.generic.rank.Rank;
import net.dungeons.generic.skills.impl.*;
import net.dungeons.generic.world.SkyblockLocation;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.pmw.tinylog.Logger;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class SkyblockPlayer extends Player {
    private SkyblockLevel skyblockLevel;
    private Rank rank;
    private AlchemySkill alchemySkill;
    private CarpentrySkill carpentrySkill;
    private CombatSkill combatSkill;
    private DungeonSkill dungeonSkill;
    private EnchantingSkill enchantingSkill;
    private FarmingSkill farmingSkill;
    private FishingSkill fishingSkill;
    private ForagingSkill foragingSkill;
    private MiningSkill miningSkill;
    private TamingSkill tamingSkill;
    private long coins;
    private long bankCoins;
    private List<SkyblockPet> pets;
    private SkyblockLocation location;

    public SkyblockPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);

        this.skyblockLevel = new SkyblockLevel(0, this);
        this.rank = Rank.DEFAULT;
        this.alchemySkill = new AlchemySkill(this);
        this.carpentrySkill = new CarpentrySkill(this);
        this.combatSkill = new CombatSkill(this);
        this.dungeonSkill = new DungeonSkill(this);
        this.enchantingSkill = new EnchantingSkill(this);
        this.farmingSkill = new FarmingSkill(this);
        this.fishingSkill = new FishingSkill(this);
        this.foragingSkill = new ForagingSkill(this);
        this.miningSkill = new MiningSkill(this);
        this.tamingSkill = new TamingSkill(this);
        this.coins = 5_000_000;
        this.bankCoins = 0;
        this.location = SkyblockLocation.DUNGEON_HUB;
        this.pets = new ArrayList<>();
    }


    public void load() {
        Document doc = Constants.playerCollection.find(eq("uuid", this.getUuid().toString())).first();

        if (doc == null)
        {
            Logger.info("New player! " + this.getName());

            this.save();

            return;
        }

        this.rank = Rank.valueOf(doc.get("rank", "DEFAULT"));
        this.coins = (long) doc.get("coins");
        this.bankCoins = (long) doc.get("bankCoins");

        SkyblockLocation lastLocation = SkyblockLocation.valueOf(doc.get("lastLocation", "DUNGEON_HUB"));
        //last location doesn't matter here as we have no reason to move the player to a different server.

        skyblockLevel.load(doc);


        List<Document> pets = (List<Document>) doc.get("pets", new ArrayList<Document>());

        if (!pets.isEmpty())
        {
            for (Document document : pets)
            {
                this.pets.add(SkyblockPet.petFromDocument(document));
            }
        }

        Document skillDoc = (Document) doc.get("skills");

        alchemySkill.load(skillDoc);
        carpentrySkill.load(skillDoc);
        combatSkill.load(skillDoc);
        dungeonSkill.load(skillDoc);
        enchantingSkill.load(skillDoc);
        farmingSkill.load(skillDoc);
        fishingSkill.load(skillDoc);
        foragingSkill.load(skillDoc);
        miningSkill.load(skillDoc);
        tamingSkill.load(skillDoc);
    }

    public void save()
    {
        Document document = new Document();

        document.put("name", this.getUsername());
        document.put("uuid", this.getUuid().toString());
        document.put("rank", rank);
        document.put("coins", coins);
        document.put("bankCoins", bankCoins);
        document.put("lastLocation", location);

        skyblockLevel.save(document);

        List<Document> petDocs = new ArrayList<>(this.pets.size());

        for (int i = 0; i < this.pets.size(); i++)
        {
            petDocs.add(this.pets.get(i).petToDocument());
        }

        document.put("pets", petDocs);

        Document skillDoc = new Document();

        alchemySkill.save(skillDoc);
        carpentrySkill.save(skillDoc);
        combatSkill.save(skillDoc);
        dungeonSkill.save(skillDoc);
        enchantingSkill.save(skillDoc);
        farmingSkill.save(skillDoc);
        fishingSkill.save(skillDoc);
        foragingSkill.save(skillDoc);
        miningSkill.save(skillDoc);
        tamingSkill.save(skillDoc);

        Constants.playerCollection.insertOne(document);
    }
}
