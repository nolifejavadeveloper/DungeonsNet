package net.dungeons.generic.scoreboard;

import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import lombok.Getter;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.util.Stringify;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.*;
import net.minestom.server.scoreboard.Scoreboard;
import net.minestom.server.scoreboard.Sidebar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.pmw.tinylog.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class SkyblockScoreboard implements Scoreboard {
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final String PREFIX = "sb-";
    private static final String TEAM_PREFIX = "sbt-";
    private static final int MAX_LINES = 15;
    private final IntLinkedOpenHashSet availableColors = new IntLinkedOpenHashSet();
    @Getter
    private final SkyblockPlayer player;
    @Getter
    private List<ScoreboardLine> lines;
    private String displayName;
    private int index = 15;
    private Component title;

    public SkyblockScoreboard(SkyblockPlayer player)
    {
        this.player = player;
        this.lines = new ArrayList<>();

        for (int i = 0; i < 16; i++)
            availableColors.add(i);

        this.displayName = PREFIX + COUNTER.getAndIncrement();
    }

    //abstract
    public abstract Component getScoreboardDisplayName();
    public abstract void init();
    public abstract void update();

    //methods

    public void updateBoard()
    {
        List<ScoreboardLine> lines1 = this.lines;

        this.lines = new ArrayList<>();

        index = 15;

        this.update();

        for (int i = 0; i < MAX_LINES; i++)
        {
            ScoreboardLine line1 = null;
            if (i < lines.size())
                line1 = lines.get(i);
            ScoreboardLine line2 = null;
            if (i < lines1.size())
                line2 = lines1.get(i);

            if (line1 == null && line2 == null)
                continue;

            if (line1 == null)
                continue;

            if (line2 == null)
                continue;

            if (line1.equals(line2))
            {
                continue;
            }

            player.sendPacket(line1.sidebarTeam.updatePrefix(line1.component));
        }
    }

    public void show()
    {
        ScoreboardObjectivePacket objectPacket = this.getCreationObjectivePacket(this.getScoreboardDisplayName(), ScoreboardObjectivePacket.Type.INTEGER);
        DisplayScoreboardPacket displayScore = this.getDisplayScoreboardPacket((byte) 1);

        player.sendPacket(objectPacket);
        player.sendPacket(displayScore);

        this.init();

        for (ScoreboardLine line : lines)
        {
            player.sendPacket(line.sidebarTeam.getCreationPacket());
            player.sendPacket(line.getScoreDestructionPacket(this.displayName));
        }
    }

    public void hide()
    {
        ScoreboardObjectivePacket obj = this.getDestructionObjectivePacket();
        player.sendPacket(obj);
        for (ScoreboardLine line : lines)
        {
            player.sendPacket(line.getScoreDestructionPacket(displayName));
            player.sendPacket(line.sidebarTeam.getDestructionPacket());
        }
    }

    public void setTitle(Component comp)
    {
        this.title = comp;
        player.sendPacket(new ScoreboardObjectivePacket(this.displayName, (byte) 2, comp, ScoreboardObjectivePacket.Type.INTEGER, null));
    }

    public Component getTitle(Component comp)
    {
        return this.title;
    }

    public void addLine(String text)
    {
        setLine(index--, text);
    }

    public void setLine(int pos, String text)
    {
        Component comp = Stringify.create(text);
        AtomicBoolean stop = new AtomicBoolean(false);
        lines.stream().filter(c -> c.position == pos || c.getComponent().equals(comp)).findFirst().ifPresent(c -> {
            if (c.getComponent().equals(comp))
            {
                if (c.position == pos)
                {
                    stop.set(true);

                    return;
                }
            }

            lines.remove(c);
        });

        if (stop.get())
        {
            return;
        }

        if (lines.size() >= MAX_LINES)
        {
            Logger.info("Cannot have more than 15 lines!");

            return;
        }

        ScoreboardLine line = createLine(pos, text);
        line.retrieveName(availableColors);
        line.createTeam();

        this.lines.add(line);

        player.sendPackets(line.sidebarTeam.getCreationPacket(), line.getScoreCreationPacket(this.getObjectiveName()));
    }

    public ScoreboardLine createLine(int pos, String text)
    {
        return new ScoreboardLine(UUID.randomUUID().toString(), Stringify.create(text), pos);
    }

    @Override
    public boolean addViewer(@NotNull Player player) {
        return false;
    }

    @Override
    public boolean removeViewer(@NotNull Player player) {
        return false;
    }

    @Override
    public @NotNull Set<@NotNull Player> getViewers() {
        return Set.of();
    }

    @Override
    public @NotNull String getObjectiveName() {
        return this.displayName;
    }

    public class ScoreboardLine
    {
        @Getter
        private final String id;
        private final Component component;
        private final String teamName;
        private int position;
        private int colorName;
        private Sidebar.NumberFormat numberFormat;
        private String entityName;
        private SidebarTeam sidebarTeam;

        public ScoreboardLine(String id, Component comp, int line)
        {
            this(id, comp, line, null);
        }

        public ScoreboardLine(@NotNull String id, @NotNull Component content, int line, @Nullable Sidebar.NumberFormat numberFormat) {
            this.id = id;
            this.component = content;
            this.position = line;
            this.numberFormat = numberFormat;

            this.teamName = TEAM_PREFIX + COUNTER.incrementAndGet();
        }

        public Component getComponent()
        {
            return sidebarTeam == null ? component : sidebarTeam.getPrefix();
        }

        private void retrieveName(IntLinkedOpenHashSet colors) {
            synchronized (colors) {
                this.colorName = colors.removeFirstInt();
            }
        }

        private void createTeam() {
            this.entityName = '§' + Integer.toHexString(colorName);
            this.sidebarTeam = new SidebarTeam(teamName, component, Component.empty(), entityName);
        }

        private void returnName(IntLinkedOpenHashSet colors) {
            synchronized (colors) {
                colors.add(colorName);
            }
        }

        private UpdateScorePacket getScoreCreationPacket(String objectiveName) {
            //TODO displayName acts as a suffix to the objective name, find way to handle elegantly
            return new UpdateScorePacket(entityName, objectiveName, position, Component.empty(), numberFormat);
        }

        private ResetScorePacket getScoreDestructionPacket(String objectiveName) {
            return new ResetScorePacket(entityName, objectiveName);
        }

        private UpdateScorePacket getLineScoreUpdatePacket(String objectiveName, int score) {
            //TODO displayName acts as a suffix to the objective name, find way to handle elegantly
            return new UpdateScorePacket(entityName, objectiveName, score, Component.empty(), numberFormat);
        }

        private void refreshComponent(Component content) {
            this.sidebarTeam.refreshPrefix(content);
        }



        public boolean equals(ScoreboardLine line)
        {
            return line.position == this.position && line.component.equals(this.component);
        }
    }

    public class SidebarTeam
    {
        private final String teamName;
        private Component prefix, suffix;
        private final String entityName;

        private final Component teamDisplayName = Component.text("displaynametest");
        private final byte friendlyFlags = 0x00;
        private final TeamsPacket.NameTagVisibility nameTagVisibility = TeamsPacket.NameTagVisibility.NEVER;
        private final TeamsPacket.CollisionRule collisionRule = TeamsPacket.CollisionRule.NEVER;
        private final NamedTextColor teamColor = NamedTextColor.DARK_GREEN;


        /**
         * The constructor to creates a team
         *
         * @param teamName   The registry name of the team
         * @param prefix     The team prefix
         * @param suffix     The team suffix
         * @param entityName The team entity name
         */
        private SidebarTeam(String teamName, Component prefix, Component suffix, String entityName) {
            this.teamName = teamName;
            this.prefix = prefix;
            this.suffix = suffix;
            this.entityName = entityName;
        }

        private TeamsPacket getDestructionPacket() {
            return new TeamsPacket(teamName, new TeamsPacket.RemoveTeamAction());
        }

        private TeamsPacket getCreationPacket() {
            final TeamsPacket.CreateTeamAction action = new TeamsPacket.CreateTeamAction(teamDisplayName, friendlyFlags,
                    nameTagVisibility, collisionRule, teamColor, prefix, suffix, List.of(entityName));
            return new TeamsPacket(teamName, action);
        }

        private TeamsPacket updatePrefix(Component prefix) {
            final TeamsPacket.UpdateTeamAction action = new TeamsPacket.UpdateTeamAction(teamDisplayName, friendlyFlags,
                    nameTagVisibility, collisionRule, teamColor, prefix, suffix);
            return new TeamsPacket(teamName, action);
        }

        private String getEntityName() {
            return entityName;
        }

        private Component getPrefix() {
            return prefix;
        }

        private void refreshPrefix(@NotNull Component prefix) {
            this.prefix = prefix;
        }

    }
}
