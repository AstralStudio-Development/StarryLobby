package cn.starry.hub;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.scoreboard.Assemble;
import cn.starry.hub.commands.admin.*;
import cn.starry.hub.commands.player.*;
import cn.starry.hub.commands.player.menu.*;
import cn.starry.hub.commands.player.time.DayCommand;
import cn.starry.hub.commands.player.time.NightCommand;
import cn.starry.hub.commands.player.time.SunsetCommand;
import cn.starry.hub.commands.rank.*;
import cn.starry.hub.functions.npc.NpcFactory;
import cn.starry.hub.functions.presents.PresentsFactory;
import cn.starry.hub.functions.runnable.RebootRunnable;
import cn.starry.hub.functions.scoreboard.Scoreboard;
import cn.starry.hub.functions.task.DeliveryDailyTask;
import cn.starry.hub.functions.task.DeliveryMonthTask;
import cn.starry.hub.listener.NameTagListener;
import cn.starry.hub.utils.menu.ButtonListener;
import cn.starry.hub.utils.menu.MenuUpdateTask;
import cn.starry.hub.utils.nametag.CautusHandler;
import dev.jnic.annotations.Include;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.event.MenuListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

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
    @Getter
    private CautusHandler cautusHandler;

    @Getter
    private PresentsFactory presentsFactory;

    @Getter
    private RebootRunnable rebootRunnable;

    @Getter
    private boolean DebugMode = false;

    @Override
    public void onEnable() {
        instance = this;

        if (!getDescription().getAuthors().contains("Stalyer") || !getDescription().getName().equals("StarryLobby")) {
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.shutdown();
        }

        saveDefaultConfig();

            loadScoreBoard();
            loadNPCs();
            loadTask();
            //loadBossBar();
            loadNameTag();
            //loadPresents();
            registerChannel();
            clearEntities();
            this.rebootRunnable = new RebootRunnable();
            this.rebootRunnable.runTaskTimerAsynchronously(this, 20, 20);

            loadMenu();
        registerCommands();
        registerListeners();

        loadSuccess();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&b插件从服务器成功卸载"));
    }

    private void clearEntities() {
        World world = Bukkit.getWorld("world");
        if (world != null) {
            for (Entity entity : world.getEntities()) {
                entity.remove();
            }
            getLogger().info("All entities in world 'world' have been removed.");
        } else {
            getLogger().warning("World 'world' not found!");
        }
    }

    private void registerCommands() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f正在尝试注册命令..."));

        registerCommand(new EditCommand());
        registerCommand(new MusicCommand());
        registerCommand(new KaboomCommand());

        registerCommand(new StuckCommand());
        registerCommand(new ShopCommand());
        registerCommand(new RankColorCommand());
        registerCommand(new SuperCommand());
        registerCommand(new DayCommand());
        registerCommand(new SunsetCommand());
        registerCommand(new NightCommand());
        registerCommand(new MenuCommand());
        registerCommand(new ProfileCommand());
        registerCommand(new SettingsCommand());

        //Ranked Player Commands
        registerCommand(new FlyCommand());
        registerCommand(new RideCommand());
        registerCommand(new SpeedCommand());
        registerCommand(new FireworkCommand());
        registerCommand(new EmojiCommand());

        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f所有命令已成功注册!"));
    }

    private void loadScoreBoard() {
        type = this.getConfig().getString("type");
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f正在尝试安装计分板..."));
        Assemble assemble = new Assemble(this, new Scoreboard());
        assemble.setTicks(1);
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f计分板已成功安装!"));
    }

    private void registerListeners() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f正在尝试注册监听器..."));

        ListenerRegistry registry = new ListenerRegistry(this);
        registry.registerListenersInPackage("cn.starry.hub.functions");
        registry.registerListenersInPackage("cn.starry.hub.listener");

        //Debug Listeners Here! Do not enable it when you are unsure of its code content
        //Bukkit.getPluginManager().registerEvents(new DebugListener(),this);

        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f所有监听器已成功注册!"));
    }

    private void loadNPCs() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f正在注册NPC..."));
        new NpcFactory().init();
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&fNPC已成功注册!"));
    }

    private void loadMenu() {
        getServer().getScheduler().runTaskTimer(this, new MenuUpdateTask(), 20L, 20L);
        getServer().getPluginManager().registerEvents(new ButtonListener(), this);
    }

    private void loadPresents() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f正在加载礼物..."));
        this.presentsFactory = new PresentsFactory();
        this.presentsFactory.init();
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f礼物已成功加载!"));
    }


    private void loadTask() {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        timer.schedule(new DeliveryDailyTask(), 0, 1000 * 60 * 60 * 24);
        timer.schedule(new DeliveryMonthTask(), date, 30L * 24L * 60L * 60L * 1000L);
    }

    private void loadNameTag() {
        this.cautusHandler = new CautusHandler(this, new NameTagListener());
        this.cautusHandler.setTicks(20);
    }

    private void loadSuccess() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f版本作者 - " + team));
        Bukkit.getConsoleSender().sendMessage(CC.translate(prefix + "&f成功安装了服务器管理器"));
    }

    private void registerCommand(Command cmd) {
        try {
            SimplePluginManager manager = (SimplePluginManager)Bukkit.getServer().getPluginManager();
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap)commandMapField.get(manager);
            Method registerMethod = CommandMap.class.getDeclaredMethod("register", String.class, Command.class);
            registerMethod.setAccessible(true);
            registerMethod.invoke(commandMap, cmd.getName(), cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerChannel() {
        //Bukkit.getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", new AntiWDL());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "mastercontrol:");
        //Register Clients - Lunar
        //Bukkit.getPluginManager().registerEvents(new LunarClient(),this);
        //new LunarClient().initClient();
    }

}
