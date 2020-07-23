package net.mcpzdevs.factioncore.commands;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private final Main plugin;

    public HealCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Player only command, send error to console
        if (!(sender instanceof Player)) {
            Utils.sendPlayerOnly(plugin, sender);
            return true;
        }

        // Check Sender Permission
        if (!sender.hasPermission("core.heal") || !sender.isOp()) {
            Utils.sendMessageString(plugin, (Player) sender, "message.no-permission", null);
            return true;
        }

        Player player = (Player) sender;
        switch (args.length) {
            case 0:
                player.setHealth(20);
                Utils.sendMessageString(plugin, player, "messages.heal.self", null);
                return true;
            case 1:
                // INFO:
                if (!sender.hasPermission("core.heal.others") || !sender.isOp()) {
                    Utils.sendMessageString(plugin, (Player) sender, "message.no-permission", null);
                    return true;
                }

                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    target.setHealth(20);

                    if (!player.equals(target)) {
                        Utils.sendMessageString(plugin, player, "messages.heal.other", null);
                        Utils.sendMessageString(plugin, target, "messages.heal.by", player);
                    } else {
                        Utils.sendMessageString(plugin, player, "messages.heal.self", null);
                    }
                } else {
                    Utils.sendOfflinePlayer(plugin, player, target);
                }
                return true;
        }
        return true;
    }
}
