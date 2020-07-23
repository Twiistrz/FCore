package net.mcpzdevs.factioncore.listeners;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    private final Main plugin;

    public JoinQuitListener(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            event.setJoinMessage(
                Utils.parseColorCodes(
                    Utils.parsePlaceholder(
                        plugin.getConfig().getString("message.firstjoin"), player, null
                    )
                )
            );
        } else {
            event.setJoinMessage(
                Utils.parseColorCodes(
                    Utils.parsePlaceholder(
                        plugin.getConfig().getString("message.join"), player, null
                    )
                )
            );
        }
    }

    /**
     *
     * @param event - Player Quit Event
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(
            Utils.parseColorCodes(
                Utils.parsePlaceholder(
                    plugin.getConfig().getString("message.quit"), player, null
                )
            )
        );
        player.saveData();
    }
}