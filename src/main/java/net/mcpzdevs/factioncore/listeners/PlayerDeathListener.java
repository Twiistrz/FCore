package net.mcpzdevs.factioncore.listeners;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlayerDeathListener implements Listener {
    private final Main plugin;

    public PlayerDeathListener(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player killer = event.getEntity().getKiller();
            Player player = event.getEntity();
            ArrayList<String> lore = new ArrayList<>();
            double worth = 124198247.32*(10.0/100.0);
            DecimalFormat worthFormat = new DecimalFormat("#,###.##");

            if (player != killer) {
                event.setDeathMessage(Utils.parseColorCodes(
                    plugin.getConfig().getString("messages.death")
                        .replace("{PLAYER}", player.getDisplayName())
                        .replace("{KILLER}", killer.getDisplayName())
                ));

                ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(byte)3);
                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                lore.add(Utils.parseColorCodes("&7Worth: &b" + worthFormat.format(worth)));
                lore.add(Utils.parseColorCodes("&7Killed by: &b" + killer.getName()));
                meta.setOwner(player.getName());
                meta.setDisplayName(Utils.parseColorCodes("&b&l" + player.getName() + "'s Head"));
                meta.setLore(lore);
                skull.setItemMeta(meta);
                player.getWorld().dropItem(player.getLocation(), skull);
                player.sendMessage(Utils.parseColorCodes("You have dropped your head!"));
            }
        }
        return;
    }
}
