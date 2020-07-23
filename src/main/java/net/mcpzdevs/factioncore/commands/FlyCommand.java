package net.mcpzdevs.factioncore.commands;

import net.mcpzdevs.factioncore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FlyCommand implements CommandExecutor {
    // Check if fly is enable
    // Check chunk if faction claim
    private final Main plugin;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
