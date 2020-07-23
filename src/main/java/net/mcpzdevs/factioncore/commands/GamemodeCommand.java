package net.mcpzdevs.factioncore.commands;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    private final Main plugin;

    public GamemodeCommand(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        // Player only command, send error to console
        if (!(sender instanceof Player)) {
            Utils.sendPlayerOnly(plugin, sender);
            return true;
        }

        // Check Sender Permission
        if (!sender.hasPermission("core.gamemode") || !sender.isOp()) {
            Utils.sendMessageString(plugin, (Player) sender, "message.no-permission", null);
            return true;
        }

        // Change Gamemode
        plugin.getGamemodeHandler().changeGamemode((Player) sender, arg, args);
        return true;
    }
}
