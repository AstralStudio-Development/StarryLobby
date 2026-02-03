package cn.starry.hub.managers;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.scoreboard.Assemble;
import cn.starry.hub.ListenerRegistry;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.database.MongoDBManager;
import cn.starry.hub.features.npc.NpcFactory;
import cn.starry.hub.features.parkour.ParkourManager;
import cn.starry.hub.features.presents.PresentsFactory;
import cn.starry.hub.task.RebootRunnable;
import cn.starry.hub.features.scoreboard.Scoreboard;
import cn.starry.hub.listeners.NameTagListener;
import cn.starry.hub.utils.menu.ButtonListener;
import cn.starry.hub.utils.menu.MenuUpdateTask;
import cn.starry.hub.utils.nametag.CautusHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

@Getter
public class LobbyManager {

    private final StarryLobby plugin;
    private MongoDBManager mongoDBManager;
    private CautusHandler cautusHandler;
    private PresentsFactory presentsFactory;
    private RebootRunnable rebootRunnable;

    public LobbyManager(StarryLobby plugin) {
        this.plugin = plugin;
    }

    public void loadAll() {
        this.mongoDBManager = new MongoDBManager(plugin);
        this.mongoDBManager.connect();
        
        new ParkourManager(plugin); // Initialize Parkour Manager
        loadScoreBoard();
        loadNPCs();
        loadNameTag();
        // loadPresents();
        registerChannel();
        clearEntities();
        startRebootRunnable();
        loadMenu();
        registerListeners();
    }

    public void close() {
        if (this.mongoDBManager != null) {
            this.mongoDBManager.close();
        }
        // Unload NPCs on disable
        try {
            if (Bukkit.getPluginManager().getPlugin("Citizens") != null && Bukkit.getPluginManager().getPlugin("Citizens").isEnabled()) {
                 NpcFactory.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadScoreBoard() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f正在尝试安装计分板..."));
        Assemble assemble = new Assemble(plugin, new Scoreboard());
        assemble.setTicks(1);
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f计分板已成功安装!"));
    }

    private void loadNPCs() {
        if (Bukkit.getPluginManager().getPlugin("Citizens") == null || !Bukkit.getPluginManager().getPlugin("Citizens").isEnabled()) {
            Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&c未找到 Citizens 插件或插件未启用，NPC功能将关闭。"));
            return;
        }
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f正在注册NPC..."));
        NpcFactory npcFactory = new NpcFactory();
        npcFactory.init();
        plugin.getServer().getPluginManager().registerEvents(npcFactory, plugin);
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&fNPC已成功注册!"));
    }

    private void loadMenu() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, new MenuUpdateTask(), 20L, 20L);
        plugin.getServer().getPluginManager().registerEvents(new ButtonListener(), plugin);
    }

    private void loadPresents() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f正在加载礼物..."));
        this.presentsFactory = new PresentsFactory();
        this.presentsFactory.init();
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f礼物已成功加载!"));
    }

    private void loadNameTag() {
        this.cautusHandler = new CautusHandler(plugin, new NameTagListener());
        this.cautusHandler.setTicks(20);
    }

    private void registerChannel() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "mastercontrol:");
    }

    private void clearEntities() {
        World world = Bukkit.getWorld("world");
        if (world != null) {
            world.getEntities().stream()
                    .filter(entity -> !(entity instanceof org.bukkit.entity.Player))
                    .filter(entity -> !entity.hasMetadata("NPC"))
                    .forEach(Entity::remove);
            plugin.getLogger().info("All entities (excluding Players and NPCs) in world 'world' have been removed.");
        } else {
            plugin.getLogger().warning("World 'world' not found!");
        }
    }

    private void startRebootRunnable() {
        this.rebootRunnable = new RebootRunnable();
        this.rebootRunnable.runTaskTimerAsynchronously(plugin, 20, 20);
    }

    private void registerListeners() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f正在尝试注册监听器..."));

        ListenerRegistry registry = new ListenerRegistry(plugin);
        registry.registerListenersInPackage("cn.starry.hub.functions");
        registry.registerListenersInPackage("cn.starry.hub.listener");

        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f所有监听器已成功注册!"));
    }
}

