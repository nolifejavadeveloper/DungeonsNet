package net.dungeons.generic.commands;

import net.dungeons.generic.messages.Messages;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.rank.Rank;
import net.dungeons.generic.util.Stringify;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;

import java.util.concurrent.CompletableFuture;

public class RankCommand extends Command {
    public RankCommand() {
        super("rank");

        setDefaultExecutor(this::sendDefaultRank);

        addSyntax(
                this::setRank,
                ArgumentType.Literal("set"),
                ArgumentType.String("username"),
                ArgumentType.String("rank")
        );

    }

    public void setRank(CommandSender sender, CommandContext args) {
        sender.sendMessage(Thread.currentThread().getName());
        SkyblockPlayer sender1 = (SkyblockPlayer) sender;
        if (!(sender1.getRank().getPermissionLevel() >= 100)) {
            sender.sendMessage(Messages.get(Messages.NO_PERMISSION));
            return;
        }

        Rank rank;
        try {
             rank = Rank.valueOf(args.get("rank").toString().toUpperCase());
        }catch (IllegalArgumentException e) {
            sender.sendMessage(Stringify.create("&cInvalid rank!"));
            return;
        }

        SkyblockPlayer target = (SkyblockPlayer) MinecraftServer.getConnectionManager().getOnlinePlayerByUsername(args.get("username").toString());

        if (target == null) {
            sender.sendMessage(Messages.get(Messages.PLAYER_NOT_FOUND));
            return;
        }

        target.sendMessage(Stringify.create("&eYour rank is being updated..."));
        sender1.sendMessage(Stringify.create("&eUpdating their rank..."));

        CompletableFuture.runAsync(() -> {
            target.setRank(rank);
            target.sendMessage(Stringify.create("&aYour rank has been updated!"));
            sender1.sendMessage(Stringify.create("&aSuccessfully changed their rank to " + rank.getDisplay(target.getCustomization())));
        });
    }

    public void sendDefaultRank(CommandSender sender, CommandContext args) {
        sender.sendMessage(Stringify.create("&aYou should buy a rank frfr helps us out a lot"));
    }
}
