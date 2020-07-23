package net.mcpzdevs.factioncore.commands;

import net.mcpzdevs.factioncore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FactionCommand implements CommandExecutor {
    // Main Faction Workaround
    // TODO: Faction functionalilty
    private final Main plugin;

    public FactionCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
