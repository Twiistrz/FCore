package net.mcpzdevs.factioncore.utils;

import net.mcpzdevs.factioncore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public static Logger logger;

    /**
     * Formatting parsing
     *
     * @param string - Long string
     * @return - Return formatted text
     */
    public static String parseColorCodes(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     *
     * Placeholders:
     * {player}, {killer}, {target}, {gamemode}, {gamemode-target}
     *
     * @param string
     * @param player
     * @param target
     * @return
     */
    public static String parsePlaceholder(String string, Player player, Player target) {
        String p1 = player.getName();
        String gm_p1 = player.getGameMode().name();
        String message = string
                .replace("{player}", p1)
                .replace("{gamemode}", gm_p1);

        if (target != null) {
            String p2 = target.getName();
            String gm_p2 = target.getGameMode().name();
            message
                .replace("{killer}", p2)
                .replace("{target}", p2)
                .replace("{gamemode-target}", gm_p2);
        }

        return message;
    }

    /**
     * Console Logs
     *
     * @param level - Log level
     * @param message - Log message
     */
    public static void sendConsoleLog(String level, String message) {
        Level getLevel;

        switch(level) {
            case "info":
                getLevel = Level.INFO;
                break;
            case "severe":
                getLevel = Level.SEVERE;
                break;
            case "warning":
                getLevel = Level.WARNING;
                break;
            default:
                getLevel = Level.FINE;
        }

        logger.log(getLevel, message);
    }

    /**
     * Send message to player with target player
     *
     * @param plugin - Main
     * @param player - Command sender
     * @param key - String key from config.yml
     * @param target - Target player
     */
    public static void sendMessageString(Plugin plugin, Player player, String key, Player target) {
        player.sendMessage(
            Utils.parseColorCodes(
                Utils.getPrefix(plugin) + Utils.parsePlaceholder(
                    plugin.getConfig().getString(key), player, target
                )
            )
        );
    }

    /**
     * Send list of message to player
     *
     * @param plugin - Main
     * @param player - Command sender
     * @param key - String key from config.yml
     */
    public static void sendMessageStringList(Plugin plugin, Player player, String key) {
        List<String> messages = plugin.getConfig().getStringList(key);
        for (String message : messages) player.sendMessage(Utils.parseColorCodes(message));
    }

    /**
     * Send unknown message to player
     *
     * @param plugin - Main
     * @param player - Command sender
     */
    public static void sendUnknownCommand(Plugin plugin, Player player) {
        Utils.sendMessageString(plugin, player, "messages.unknown-command", null);
    }

    /**
     * Send player is offline to command sender
     *
     * @param plugin - Main java
     * @param player - Command sender
     * @param target - Target player
     */
    public static void sendOfflinePlayer(Plugin plugin, Player player, Player target) {
        Utils.sendMessageString(plugin, player, "messages.offline", target);
    }

    /**
     * Send player only command to console
     *
     * @param plugin - Main
     * @param console - Console
     */
    public static void sendPlayerOnly(Plugin plugin, CommandSender console) {
        console.sendMessage(
            Utils.parseColorCodes(
                plugin.getConfig().getString("messages.console")
            )
        );
    }

    /**
     * Prefix constructor
     *
     * @param plugin - Main
     * @return - Return prefix
     */
    public static String getPrefix(Plugin plugin) {
        String prefix = plugin.getConfig().getString("messages.prefix");
        if (!prefix.isEmpty()) return prefix + " ";
        else return "";
    }
}
