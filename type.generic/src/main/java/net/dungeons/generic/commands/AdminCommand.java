package net.dungeons.generic.commands;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.rank.Rank;
import net.dungeons.generic.util.Stringify;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AdminCommand extends Command {
    public AdminCommand() {
        super("admin", "sbadmin");

        setDefaultExecutor(this::usage);

        this.addSyntax(
                this::setCoins,
                ArgumentType.Literal("set"),
                ArgumentType.Literal("coins"),
                ArgumentType.Long("amount")
        );

        this.addSyntax(
                this::setCoins,
                ArgumentType.Literal("set"),
                ArgumentType.Literal("coins"),
                ArgumentType.Long("amount"),
                ArgumentType.Entity("player").onlyPlayers(true)
        );
    }

    private void setCoins(CommandSender sender, CommandContext args)
    {
        SkyblockPlayer player = null;

        if (args.has("player"))
        {
            player = args.get("player");

            if (sender instanceof SkyblockPlayer p)
            {
                if (!p.getRank().hasPermission(Rank.MOD))
                {
                    sendDefault(sender);
                }
            }
        }
        else if (!(sender instanceof SkyblockPlayer))
        {
            sender.sendMessage(Stringify.create("&cConsole player must pass a player!"));

            return;
        }
        else
        {
            player = (SkyblockPlayer) sender;

            if (!player.getRank().hasPermission(Rank.MOD))
            {
                sendDefault(sender);

                return;
            }
        }

        player.setCoins(args.get("amount"));
    }

    private void sendDefault(CommandSender sender)
    {
        sender.sendMessage(Stringify.create("&c/admin"));
    }

    private void usage(CommandSender sender, CommandContext args)
    {
        if (sender instanceof SkyblockPlayer player)
        {
            if (!player.getRank().hasPermission(Rank.MOD))
            {
                sendDefault(sender);

                return;
            }
        }

        sendDefault(sender);
    }

    private boolean isAllowed()
    {
        return true;
    }
}
