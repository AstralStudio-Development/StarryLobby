package cn.starry.hub;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.database.MongoDBManager;
import cn.starry.hub.features.presents.PresentsFactory;
import cn.starry.hub.task.RebootRunnable;
import cn.starry.hub.managers.CommandRegistry;
import cn.starry.hub.managers.LobbyManager;
import cn.starry.hub.utils.nametag.CautusHandler;
import dev.jnic.annotations.Include;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Author: Starry_Killer, Qlickly_, CloudForeal
 * @Date: 2023/06/14
 */

@Include
public final class StarryLobby extends JavaPlugin {

    @Getter
    private static StarryLobby instance;
    @Getter
    private String prefix = "&f[&bStarry&3Lobby&f] ";
    @Getter
    private String type;
    @Getter
    private String version = "StarryCountry";
    @Getter
    private String team = "Starry_Killer";

    @Getter
    private boolean proxy;
    @Getter
    private boolean depend = true;
    @Getter
    public boolean economy = true;
    @Getter
    public boolean rewards = true;
    @Getter
    public boolean horn = true;
    
    private LobbyManager lobbyManager;
    
    @Getter
    private boolean DebugMode = false;

    @Override
    public void onEnable() {
        instance = this;

        if (!getDescription().getAuthors().contains("Stalyer") || !getDescription().getName().equals("StarryLobby")) {
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.shutdown();
            return;
        }

        saveDefaultConfig();
        this.type = getConfig().getString("type");

        // Initialize Managers
        this.lobbyManager = new LobbyManager(this);
        CommandRegistry commandRegistry = new CommandRegistry(this);

        // Load Systems
        this.lobbyManager.loadAll();
        commandRegistry.registerAll();

        loadSuccess();
    }

    @Override
    public void onDisable() {
        if (lobbyManager != null) {
            lobbyManager.close();
        }
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&b插件从服务器成功卸载"));
    }

    private void loadSuccess() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f版本作者 - " + team));
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f成功安装了服务器管理器"));
    }
    
    // Delegate getters to LobbyManager for backward compatibility
    public CautusHandler getCautusHandler() {
        return lobbyManager.getCautusHandler();
    }

    public PresentsFactory getPresentsFactory() {
        return lobbyManager.getPresentsFactory();
    }

    public RebootRunnable getRebootRunnable() {
        return lobbyManager.getRebootRunnable();
    }

    public MongoDBManager getMongoDBManager() {
        return lobbyManager.getMongoDBManager();
    }
}

