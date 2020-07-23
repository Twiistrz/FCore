package net.mcpzdevs.factioncore.handlers;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeHandler {
    private final Main plugin;

    public GamemodeHandler(Main plugin) {
        this.plugin = plugin;
    }

    public boolean changeGamemode(Player player, String arg, String[] args) {
        // TODO: Need to optimize this code
        switch (args.length) {
            case 0:
                if (arg.equalsIgnoreCase("gamemode")) {
                    player.sendMessage(Utils.parseColorCodes("&cUsage: /gamemode <adventure|creative|survival|spectator> [player]"));
                    return true;
                }

                if (arg.equalsIgnoreCase("gma") || arg.equalsIgnoreCase("adventure")) {
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (arg.equalsIgnoreCase("gmc") || arg.equalsIgnoreCase("creative")) {
                    player.setGameMode(GameMode.CREATIVE);
                } else if (arg.equalsIgnoreCase("gms") || arg.equalsIgnoreCase("survival")) {
                    player.setGameMode(GameMode.SURVIVAL);
                } else if (arg.equalsIgnoreCase("gmsp") || arg.equalsIgnoreCase("spectator")) {
                    player.setGameMode(GameMode.SPECTATOR);
                }

                Utils.sendMessageString(plugin, player, "messages.gamemode.success", null);
                return true;
            case 1:
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    if (arg.equalsIgnoreCase("gma") || arg.equalsIgnoreCase("adventure")) {
                        target.setGameMode(GameMode.ADVENTURE);
                    } else if (arg.equalsIgnoreCase("gmc") || arg.equalsIgnoreCase("creative")) {
                        target.setGameMode(GameMode.CREATIVE);
                    } else if (arg.equalsIgnoreCase("gms") || arg.equalsIgnoreCase("survival")) {
                        target.setGameMode(GameMode.SURVIVAL);
                    } else if (arg.equalsIgnoreCase("gmsp") || arg.equalsIgnoreCase("spectator")) {
                        target.setGameMode(GameMode.SPECTATOR);
                    } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                        player.setGameMode(GameMode.ADVENTURE);
                    } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                        player.setGameMode(GameMode.CREATIVE);
                    } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                        player.setGameMode(GameMode.SURVIVAL);
                    } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                        player.setGameMode(GameMode.SPECTATOR);
                    }

                    if (player.equals(target)) {
                        Utils.sendMessageString(plugin, player, "messages.gamemode.other", target);
                        Utils.sendMessageString(plugin, target, "messages.gamemode.success", null);
                    } else {
                        Utils.sendMessageString(plugin, player, "message.gamemode.success", null);
                    }
                } else {
                    Utils.sendOfflinePlayer(plugin, player, target);
                }
                return true;
            case 2:
                target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                        target.setGameMode(GameMode.ADVENTURE);
                    } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                        target.setGameMode(GameMode.CREATIVE);
                    } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                        target.setGameMode(GameMode.SURVIVAL);
                    } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                        target.setGameMode(GameMode.SPECTATOR);
                    }

                    if (player.equals(target)) {
                        Utils.sendMessageString(plugin, player, "messages.gamemode.other", target);
                        Utils.sendMessageString(plugin, target, "messages.gamemode.success", null);
                    } else {
                        Utils.sendMessageString(plugin, player, "message.gamemode.success", null);
                    }
                } else {
                    Utils.sendOfflinePlayer(plugin, player, target);
                }
                return true;
        }

        return true;
    }
}
