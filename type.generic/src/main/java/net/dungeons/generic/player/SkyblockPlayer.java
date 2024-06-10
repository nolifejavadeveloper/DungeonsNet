package net.dungeons.generic.player;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.Constants;
import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.items.SkyblockItemFactory;
import net.dungeons.generic.items.SkyblockItemRegistry;
import net.dungeons.generic.level.SkyblockLevel;
import net.dungeons.generic.pet.SkyblockPet;
import net.dungeons.generic.rank.Rank;
import net.dungeons.generic.scoreboard.SkyblockScoreboard;
import net.dungeons.generic.skills.impl.*;
import net.dungeons.generic.util.Stringify;
import net.dungeons.generic.world.SkyblockLocation;
import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
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
    private PreferredCustomization customization;
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
    private SkyblockScoreboard scoreboard;
    private long coins;
    private long bankCoins;
    private List<SkyblockPet> pets;
    private SkyblockLocation location;
    private SkyblockItem currentItem = null;

    public SkyblockPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);

        this.skyblockLevel = new SkyblockLevel(0, this);
        this.rank = Rank.DEFAULT;
        this.customization = new PreferredCustomization('6');
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
        this.scoreboard = null;
    }

    public void load() {
        Document doc = Constants.playerCollection.find(eq("uuid", this.getUuid().toString())).first();

        if (doc == null)
        {
            Logger.info("New player! " + this.getUsername());

            this.save(true);

            return;
        }

        this.rank = Rank.valueOf(doc.get("rank", "DEFAULT"));
        this.coins = (long) doc.get("coins");
        this.bankCoins = (long) doc.get("bankCoins");

        Document custom = (Document) doc.get("customization");
        this.customization.load(custom);

        SkyblockLocation lastLocation = SkyblockLocation.valueOf(doc.get("lastLocation", "DUNGEON_HUB"));
        //last location doesn't matter here as we have no reason to move the player to a different server.

        skyblockLevel.load(doc);


        List<Document> pets = doc.get("pets", new ArrayList<Document>());

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
        this.save(false);
    }

    public void save(boolean first)
    {
        Document document = new Document();

        document.put("name", this.getUsername());
        document.put("uuid", this.getUuid().toString());
        document.put("rank", rank);
        document.put("coins", coins);
        document.put("bankCoins", bankCoins);
        document.put("lastLocation", location);

        Document custom = new Document();
        {
            customization.save(custom);

            document.put("customization", custom);
        }

        skyblockLevel.save(document);

        List<Document> petDocs = new ArrayList<>(this.pets.size());

        for (int i = 0; i < this.pets.size(); i++)
        {
            petDocs.add(this.pets.get(i).petToDocument());
        }

        document.put("pets", petDocs);

        Document skillDoc = new Document();
        {
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

            document.put("skills", skillDoc);
        }

        if (first)
        {
            Constants.playerCollection.insertOne(document);
        }
        else
        {
            Constants.playerCollection.replaceOne(eq("uuid", this.getUuid().toString()), document);
        }
    }

    public void processChatMessage(String message)
    {
        String line = Stringify.formatString(skyblockLevel.getFormatted()
        + " " + rank.getDisplay(this.customization) + this.getUsername() + "&" + rank.getChatColor() + ": ");

        line += message;

        for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers())
        {
            player.sendMessage(Component.text(line));
        }
    }

    public void showScoreboard(SkyblockScoreboard scoreboard)
    {
        this.scoreboard = scoreboard;

        scoreboard.show();
    }

    public void tick()
    {
        if (this.scoreboard != null && this.getAliveTicks() % 2 == 0)
        {
            this.scoreboard.updateSidebar();
        }

        /*if (!(this.getInventory().getItemInMainHand() instanceof SkyblockItem) && this.getInventory().getItemInMainHand() != ItemStack.AIR)
        {
            this.getInventory().setItemInMainHand(SkyblockItemFactory.convertNonToSkyblock(this.getItemInMainHand(), this));
        }

        if (this.getInventory().getItemInMainHand() instanceof SkyblockItem) {
            SkyblockItem itemInHand = (SkyblockItem) this.getItemInMainHand();

            if (itemInHand != currentItem) {
                this.onChangeHeldItem(currentItem, itemInHand);

                currentItem = itemInHand;
            }
        }*/
    }

    public void onChangeHeldItem(SkyblockItem prev, SkyblockItem current)
    {
        this.getInventory().setItemInMainHand(current);
    }
}
