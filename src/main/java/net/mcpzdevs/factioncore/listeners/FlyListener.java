package net.mcpzdevs.factioncore.listeners;

import net.mcpzdevs.factioncore.Main;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlyListener implements Listener {
    private final Main plugin;

    public FlyListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // TODO: Check fly enabled and faction claim area
        if (player.isFlying()) {
            player.setFlying(true);
            player.setAllowFlight(true);
            Utils.parseColorCodes("&aFly already enabled");
        }

        /*
        if (player.isFlying() && Faction.territoryOwn()) {
            player.setFlying(false);
            player.setAllowFlight(false);
            Utils.parseColorCodes("&cFlight disabled!");
        }
         */

        // TODO: If not in claim area
        // player.setFlying(false);
        // player.setAllowFlight(false);
    }
}
