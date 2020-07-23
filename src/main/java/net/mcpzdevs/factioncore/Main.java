package net.mcpzdevs.factioncore;

import net.mcpzdevs.factioncore.commands.*;
import net.mcpzdevs.factioncore.handlers.GamemodeHandler;
import net.mcpzdevs.factioncore.listeners.JoinQuitListener;
import net.mcpzdevs.factioncore.listeners.PlayerDeathListener;
import net.mcpzdevs.factioncore.utils.Utils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Faction Core
 * @author Twiistrz and CodexApple
 */
public class Main extends JavaPlugin {
    private static GamemodeHandler gamemodeHandler;

    @Override
    public void onEnable() {
        Utils.logger = getLogger();
        saveDefaultConfig();
        registerCommands();
        registerHandlers();
        registerListeners();
        Utils.sendConsoleLog("info", "Faction Core Enabled!");
    }

    /**
     * Register Commands
     */
    private void registerCommands() {
        // Commands: Help, Core, Gamemode, Heal, Fly, Faction, Feed, Grace, TNT, Printer,
        getCommand("help").setExecutor(new HelpCommand(this));
        getCommand("core").setExecutor(new CoreCommand(this));
        getCommand("gamemode").setExecutor(new GamemodeCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("faction").setExecutor(new FactionCommand(this));
    }

    /**
     * Register Handlers
     */
    private void registerHandlers() {
        gamemodeHandler = new GamemodeHandler(this);
    }

    /**
     * Register Listeners
     */
    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinQuitListener(this), (Plugin) this);
        pluginManager.registerEvents(new PlayerDeathListener(this), (Plugin) this);
    }

    /**
     * Get Handlers
     * @return - Return handlers
     */
    public GamemodeHandler getGamemodeHandler() {
        return gamemodeHandler;
    }
}