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
import net.minestom.server.utils.validate.Check;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.pmw.tinylog.Logger;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class SkyblockScoreboard implements Scoreboard {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    /**
     * <b>WARNING:</b> You should NOT create any scoreboards/teams with the same prefixes as those
     */
    private static final String SCOREBOARD_PREFIX = "sb-";
    private static final String TEAM_PREFIX = "sbt-";

    /**
     * Limited by the notch client, do not change
     */
    private static final int MAX_LINES_COUNT = 15;
    private final Set<ScoreboardLine> lines = new CopyOnWriteArraySet<>();
    private final IntLinkedOpenHashSet availableColors = new IntLinkedOpenHashSet();
    private final String objectiveName;
    @Getter
    private final SkyblockPlayer player;
    private Component comp;


    public SkyblockScoreboard(SkyblockPlayer player) {
        this.objectiveName = SCOREBOARD_PREFIX + COUNTER.incrementAndGet();
        this.player = player;

        // Fill available colors for entities name showed in scoreboard
        for (int i = 0; i < 16; i++) {
            availableColors.add(i);
        }

        comp = Component.empty();
    }

    public abstract Component getTitle();
    public abstract void update();

    public Component getScoreboardTitle()
    {
        this.comp = this.getTitle();

        return this.comp;
    }

    public void updateSidebar()
    {
        Component prev = this.comp;
        Component c = this.getTitle();

        List<ScoreboardLine> newLines = new ArrayList<>();
        newLines.addAll(lines);

        this.update();

        if (!prev.equals(c))
        {
            this.setTitle(c);
        }
    }

    public void setTitle(@NotNull Component title) {
        this.player.sendPacket(new ScoreboardObjectivePacket(objectiveName, (byte) 2, title,
                ScoreboardObjectivePacket.Type.INTEGER, null));
    }

    public void createLine(@NotNull ScoreboardLine scoreboardLine) {
        synchronized (lines) {
            Check.stateCondition(lines.size() >= MAX_LINES_COUNT, "You cannot have more than " + MAX_LINES_COUNT + "  lines");
            Check.argCondition(lines.contains(scoreboardLine), "You cannot add two times the same ScoreboardLine");

            // Check ID duplication
            for (ScoreboardLine line : lines) {
                Check.argCondition(line.id.equals(scoreboardLine.id),
                        "You cannot add two ScoreboardLine with the same id");
            }

            // Setup line
            scoreboardLine.retrieveName(availableColors);
            scoreboardLine.createTeam();

            // Finally add the line in cache
            this.lines.add(scoreboardLine);

            // Send to current viewers
            player.sendPackets(scoreboardLine.sidebarTeam.getCreationPacket(), scoreboardLine.getScoreCreationPacket(objectiveName));
        }
    }

    @Override
    public boolean addViewer(@NotNull Player player) {
        return false;
    }

    @Override
    public boolean removeViewer(@NotNull Player player) {
        return false;
    }

    public void updateLineContent(@NotNull String id, @NotNull Component content) {
        final ScoreboardLine scoreboardLine = getLine(id);
        if (scoreboardLine != null) {
            scoreboardLine.refreshContent(content);
            player.sendPacket(scoreboardLine.sidebarTeam.updatePrefix(content));
        }
    }

    public void updateLineScore(@NotNull String id, int score) {
        final ScoreboardLine scoreboardLine = getLine(id);
        if (scoreboardLine != null) {
            scoreboardLine.line = score;
            player.sendPacket(scoreboardLine.getLineScoreUpdatePacket(objectiveName, score));
        }
    }

    public void addLine(int pos, Component comp)
    {
        for (ScoreboardLine line : lines)
        {
            if (line.line == pos && line.content.equals(comp))
                return;

            if (line.line == pos)
            {
                this.removeLine(line.id);
            }
            else if (line.content.equals(comp))
            {
                line.line = pos;
                player.sendPacket(line.getLineScoreUpdatePacket(this.objectiveName, pos));
                return;
            }
        }

        createLine(new ScoreboardLine(UUID.randomUUID().toString(), comp, pos));
    }

    public void addLine(int pos, String text)
    {
        this.addLine(pos, Stringify.create(text));
    }

    @Nullable
    public ScoreboardLine getLine(@NotNull String id) {
        for (ScoreboardLine line : lines) {
            if (line.id.equals(id))
                return line;
        }
        return null;
    }

    @NotNull
    public Set<ScoreboardLine> getLines() {
        return Collections.unmodifiableSet(lines);
    }

    public void removeLine(@NotNull String id) {
        this.lines.removeIf(line -> {
            if (line.id.equals(id)) {

                // Remove the line for current viewers
                player.sendPackets(line.getScoreDestructionPacket(objectiveName), line.sidebarTeam.getDestructionPacket());

                line.returnName(availableColors);
                return true;
            }
            return false;
        });
    }

    public boolean show()
    {
        this.updateSidebar();

        ScoreboardObjectivePacket scoreboardObjectivePacket = this.getCreationObjectivePacket(this.getTitle(), ScoreboardObjectivePacket.Type.INTEGER);
        DisplayScoreboardPacket displayScoreboardPacket = this.getDisplayScoreboardPacket((byte) 1);

        player.sendPacket(scoreboardObjectivePacket); // Creative objective
        player.sendPacket(displayScoreboardPacket); // Show sidebar scoreboard (wait for scores packet)

        for (ScoreboardLine line : lines) {
            player.sendPacket(line.sidebarTeam.getCreationPacket());
            player.sendPacket(line.getScoreCreationPacket(objectiveName));
        }

        return true;
    }

    public boolean hide() {
        ScoreboardObjectivePacket scoreboardObjectivePacket = this.getDestructionObjectivePacket();
        player.sendPacket(scoreboardObjectivePacket);
        for (ScoreboardLine line : lines) {
            player.sendPacket(line.getScoreDestructionPacket(objectiveName)); // Is it necessary?
            player.sendPacket(line.sidebarTeam.getDestructionPacket());
        }
        return true;
    }

    @Override
    public @NotNull String getObjectiveName() {
        return this.objectiveName;
    }

    /**
     * This class is used to create a line for the sidebar.
     */
    public static class ScoreboardLine {

        /**
         * The identifier is used to modify the line later
         */
        private final String id;
        /**
         * The content for the line
         */
        private final Component content;
        /**
         * The score of the line
         * -- GETTER --
         *  Gets the position of the line
         *
         * @return the line position

         */
        @Getter
        private int line;
        /**
         * The number format of the line
         */
        private Sidebar.NumberFormat numberFormat;

        private final String teamName;
        /**
         * The name of the score ({@code entityName}) which is essentially an identifier
         */
        private int colorName;
        private String entityName;
        /**
         * The sidebar team of the line
         */
        private SidebarTeam sidebarTeam;

        public ScoreboardLine(@NotNull String id, @NotNull Component content, int line) {
            this(id, content, line, null);
        }

        public ScoreboardLine(@NotNull String id, @NotNull Component content, int line, @Nullable Sidebar.NumberFormat numberFormat) {
            this.id = id;
            this.content = content;
            this.line = line;
            this.numberFormat = numberFormat;

            this.teamName = TEAM_PREFIX + COUNTER.incrementAndGet();
        }

        /**
         * Gets the identifier of the line
         *
         * @return the line identifier
         */
        public @NotNull String getId() {
            return id;
        }

        /**
         * Gets the content of the line
         *
         * @return The line content
         */
        public @NotNull Component getContent() {
            return sidebarTeam == null ? content : sidebarTeam.getPrefix();
        }

        private void retrieveName(IntLinkedOpenHashSet colors) {
            synchronized (colors) {
                this.colorName = colors.removeFirstInt();
            }
        }

        /**
         * Creates a new {@link SidebarTeam}
         */
        private void createTeam() {
            this.entityName = '§' + Integer.toHexString(colorName);
            this.sidebarTeam = new SidebarTeam(teamName, content, Component.empty(), entityName);
        }

        private void returnName(IntLinkedOpenHashSet colors) {
            synchronized (colors) {
                colors.add(colorName);
            }
        }

        /**
         * Gets a score creation packet
         *
         * @param objectiveName The objective name to be updated
         * @return a {@link UpdateScorePacket}
         */
        private UpdateScorePacket getScoreCreationPacket(String objectiveName) {
            //TODO displayName acts as a suffix to the objective name, find way to handle elegantly
            return new UpdateScorePacket(entityName, objectiveName, line, Component.empty(), numberFormat);
        }

        /**
         * Gets a score destruction packet
         *
         * @param objectiveName The objective name to be destroyed
         * @return a {@link UpdateScorePacket}
         */
        private ResetScorePacket getScoreDestructionPacket(String objectiveName) {
            return new ResetScorePacket(entityName, objectiveName);
        }

        /**
         * Gets a line score update packet
         *
         * @param objectiveName The objective name to be updated
         * @param score         The new score
         * @return a {@link UpdateScorePacket}
         */
        private UpdateScorePacket getLineScoreUpdatePacket(String objectiveName, int score) {
            //TODO displayName acts as a suffix to the objective name, find way to handle elegantly
            return new UpdateScorePacket(entityName, objectiveName, score, Component.empty(), numberFormat);
        }

        /**
         * Refresh the prefix of the {@link SidebarTeam}
         *
         * @param content The new content
         */
        private void refreshContent(Component content) {
            this.sidebarTeam.refreshPrefix(content);
        }

    }

    private static class SidebarTeam {

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

        /**
         * Gets a team creation packet
         *
         * @return a {@link TeamsPacket} which creates a new team
         */
        private TeamsPacket getCreationPacket() {
            final var action = new TeamsPacket.CreateTeamAction(teamDisplayName, friendlyFlags,
                    nameTagVisibility, collisionRule, teamColor, prefix, suffix, List.of(entityName));
            return new TeamsPacket(teamName, action);
        }

        /**
         * Gets a team destruction packet
         *
         * @return a {@link TeamsPacket} which destroyed a team
         */
        private TeamsPacket getDestructionPacket() {
            return new TeamsPacket(teamName, new TeamsPacket.RemoveTeamAction());
        }

        /**
         * Updates the prefix of the {@link SidebarTeam}
         *
         * @param prefix The new prefix
         * @return a {@link TeamsPacket} with the updated prefix
         */
        private TeamsPacket updatePrefix(Component prefix) {
            final var action = new TeamsPacket.UpdateTeamAction(teamDisplayName, friendlyFlags,
                    nameTagVisibility, collisionRule, teamColor, prefix, suffix);
            return new TeamsPacket(teamName, action);
        }

        /**
         * Gets the entity name of the team
         *
         * @return the entity name
         */
        private String getEntityName() {
            return entityName;
        }

        /**
         * Gets the prefix of the team
         *
         * @return the prefix
         */
        private Component getPrefix() {
            return prefix;
        }

        /**
         * Refresh the prefix of the {@link SidebarTeam}
         *
         * @param prefix The refreshed prefix
         */
        private void refreshPrefix(@NotNull Component prefix) {
            this.prefix = prefix;
        }
    }
}
