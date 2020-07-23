package net.mcpzdevs.factioncore.commands;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    private final Main plugin;

    public HelpCommand(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        // Player only command, send error to console
        if (!(sender instanceof Player)) {
            Utils.sendPlayerOnly(plugin, sender);
            return true;
        }

        // No need to check permissionos
        // Display Help Message
        Utils.sendMessageStringList(plugin, (Player) sender, "messages.help.general");
        return true;
    }
}
