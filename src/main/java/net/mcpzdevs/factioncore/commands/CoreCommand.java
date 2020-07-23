package net.mcpzdevs.factioncore.commands;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CoreCommand implements CommandExecutor {
    private final Main plugin;

    public CoreCommand(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.sendPlayerOnly(plugin, sender);
            return true;
        }

        // Check Perms
        if (!sender.hasPermission("core.main") || !sender.isOp()) {
            Utils.sendMessageString(plugin, (Player) sender, "message.no-permission", null);
            return true;
        }

        // Display Core
        switch(args.length) {
            case 0:
                // Display Core Help Messages
                Utils.sendMessageStringList(plugin, (Player) sender, "messages.core");
                return true;
            case 1:
                // Core first argument
                if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        plugin.getConfig().load(new File("plugins" + System.getProperty("file.separator") + plugin.getDescription().getName() + System.getProperty("file.separator") + "config.yml"));
                    } catch (IOException error) {
                        Utils.sendConsoleLog("severe", error.getMessage());
                        Utils.sendMessageString(plugin, (Player) sender, "messages.reload.failed", null);
                        return true;
                    } catch (InvalidConfigurationException error) {
                        Utils.sendConsoleLog("severe", error.getMessage());
                        Utils.sendMessageString(plugin, (Player) sender, "messages.reload.failed", null);
                        return true;
                    }
                    Utils.sendMessageString(plugin, (Player) sender, "messages.reload.success", null);
                    return true;
                }
        }

        Utils.sendUnknownCommand(plugin, (Player) sender);
        return true;
    }
}
