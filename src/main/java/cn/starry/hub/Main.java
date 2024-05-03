package cn.starry.hub;

import cn.starry.hub.commands.admin.*;
import cn.starry.hub.commands.player.*;
import cn.starry.hub.commands.player.menu.MenuCommand;
import cn.starry.hub.commands.player.menu.ProfileCommand;
import cn.starry.hub.commands.player.menu.RankColorCommand;
import cn.starry.hub.commands.player.menu.SettingsCommand;
import cn.starry.hub.commands.player.time.DayCommand;
import cn.starry.hub.commands.player.time.NightCommand;
import cn.starry.hub.commands.player.time.SunsetCommand;
import cn.starry.hub.commands.rank.*;
import cn.starry.hub.commands.substituted.ListCommand;
import cn.starry.hub.commands.substituted.TeleportCommand;
import cn.starry.hub.database.MongoDB;
import cn.starry.hub.functions.achievement.AchievementFactory;
import cn.starry.hub.functions.activity.springfestival.Y2024.presents.PresentsFactory;
import cn.starry.hub.functions.activity.springfestival.Y2024.presents.PresentsListener;
import cn.starry.hub.functions.menu.achievements.AchievementsMenu;
import cn.starry.hub.functions.menu.achievements.AchievementsSubMenu;
import cn.starry.hub.functions.menu.achievements.ChallengePageMenu;
import cn.starry.hub.functions.menu.agreement.AgreementMenu;
import cn.starry.hub.functions.menu.profile.CustomViewMenu;
import cn.starry.hub.functions.menu.profile.DeliveryMenu;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.functions.menu.profile.RankColorMenu;
import cn.starry.hub.functions.menu.profile.settings.ChatSettingsMenu;
import cn.starry.hub.functions.menu.profile.settings.LobbySettingsMenu;
import cn.starry.hub.functions.menu.social.FriendsMenu;
import cn.starry.hub.functions.menu.social.PartyMenu;
import cn.starry.hub.functions.menu.social.RecentPlayersMenu;
import cn.starry.hub.functions.runnable.RebootRunnable;
import cn.starry.hub.functions.scoreboard.Scoreboard;
import cn.starry.hub.functions.settings.PlayerReceiveEvent;
import cn.starry.hub.functions.task.DeliveryDailyTask;
import cn.starry.hub.functions.task.DeliveryMonthTask;
import cn.starry.hub.listener.NameTagListener;
import cn.starry.hub.listener.handler.TabHandler;
import cn.starry.hub.parm.RegisterListener;
import cn.starry.hub.support.placeholderapi.PlaceHolderAPIHook;
import cn.starry.hub.support.placeholderapi.TotalExpand;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.nametag.NametagHandler;
import cn.starry.hub.functions.CheckWipe;
import cn.starry.hub.functions.FunCube;
import cn.starry.hub.functions.SlumberHotel;
import cn.starry.hub.functions.npc.NpcFactory;
import cn.starry.hub.listener.PlayerPreLoginListener;
import cn.starry.hub.listener.handler.BossBarHandler;
import cn.starry.hub.utils.scoreboard.Assemble;
import lombok.Getter;
import net.jitse.npclib.NPCLib;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @Author: Starry_Killer, Qlickly_, CloudForeal
 * @Date: 2023/06/14
 */

public final class Main extends JavaPlugin {

    public static JavaPlugin plugin;

    @Getter
    private static Main instance;
    @Getter
    private MongoDB data;
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
    public boolean presents = false;
    @Getter
    private NPCLib npcLib;
    @Getter
    private AchievementFactory achievementFactory;
    @Getter
    private BossBarHandler bossBar;
    @Getter
    private FunCube funCube;
    @Getter
    private NametagHandler nametagHandler;
    @Getter
    private PresentsFactory presentsFactory;
    @Getter
    private RebootRunnable rebootRunnable;

    @Getter
    private boolean DebugMode = false;

    @Override
    public void onEnable() {
        instance = this;
        data = new MongoDB();

        if (!getDescription().getAuthors().contains("Starry_Killer") || !getDescription().getName().equals("StarryLobby")) {
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.shutdown();
        }

        //Servers.yml
        File configFile = new File(this.getDataFolder(), "servers.yml");
        if (!configFile.exists()) {
            this.saveResource("servers.yml", false);
        }
        //
        saveDefaultConfig();

        if (!getConfig().getBoolean("apiMode")) {
            loadServer();
            loadDepend();
            loadScoreBoard();
            loadNPCs();
            loadTask();
            new TotalExpand().register();
            //loadBossBar();
            loadNameTag();
            loadPresents();
            registerChannel();
            clearEntities();
            this.rebootRunnable = new RebootRunnable();
            this.rebootRunnable.runTaskTimerAsynchronously(this, 20, 20);
            if (getConfig().getString("type").equalsIgnoreCase("Lobby")) {
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    //funCube = new FunCube();
                    //funCube.createArmorStand();
                }, 40L);
            }
            if (getConfig().getString("type").equalsIgnoreCase("BedWars")) {
                //new SlimeBlockTask();
                //new SlumberDoorTask();
                Bukkit.getPluginManager().registerEvents(new SlumberHotel(),this);
            }
        }
        new PlaceHolderAPIHook().register();
        loadAchievements();
        registerCommands();
        registerListeners();

        PlayerReceiveEvent.loadSensitiveWords();
        PlayerReceiveEvent.loadPrefabricationMessages();

        loadSuccess();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&b插件从服务器成功卸载"));
        if (getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            //funCube.removeArmorStand();
        }
        if (getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            //SlimeBlockTask.removeTask();
            //SlumberDoorTask.removeTask();
        }
    }

    private void loadServer() {
        proxy = this.getConfig().getBoolean("proxy");
        if (proxy) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f服务器代理模式: 插件"));
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f服务器代理模式: 无"));
            this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        }
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f服务器核心版本号: " + Bukkit.getVersion()));
    }

    private void loadDepend() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在检索所需前置插件"));
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f你需要 &cLuckPerms &f来驱动插件"));
            depend = false;
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f已安装 &aLuckPerms"));
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f你需要 &cPlaceHolderAPI &f来驱动插件"));
            depend = false;
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f已安装 &aPlaceHolderAPI"));
        }
        if (Bukkit.getPluginManager().getPlugin("PlayerPoints") == null) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f你需要 &cPlayerPoints &f来驱动经济系统"));
            economy = false;
            depend = false;
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f已安装 &aPlayerPoints"));
            economy = true;
        }
        if (Bukkit.getPluginManager().getPlugin("AlonsoLevels") == null) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f你需要 &cAlonsoLevels &f来驱动奖励与等级"));
            rewards = false;
            depend = false;
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f已安装 &aAlonsoLevels"));
            rewards = true;
        }
        if (Bukkit.getPluginManager().getPlugin("PKLaBaAddon") == null) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f你需要 &cPKLaBaAddon &f来驱动喇叭"));
            horn = false;
            depend = false;
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f已安装 &aPKLaBaAddon"));
            horn = true;
        }
        if (!depend) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f请检查前置插件安装情况,若缺少前置或将导致未知的问题出现"));
        }
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
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在尝试注册命令..."));

        if (!getConfig().getBoolean("apiMode")) {
            //Admin Commands
            registerCommand(new RebootCommand());
            registerCommand(new FakeOPCommand());
            registerCommand(new WhoCommand());
            registerCommand(new EventCommand());
            registerCommand(new AchievementCommand());
            registerCommand(new BoosterCommand());
            registerCommand(new WipeCommand());
            registerCommand(new EditCommand());
            registerCommand(new SendCommand());
            registerCommand(new NickCommand());
            registerCommand(new UnNickCommand());
            registerCommand(new VanishCommand());
            //Player Commands
            registerCommand(new RankColorCommand());
            registerCommand(new PresentsCommand());
            //registerCommand(new ManagerCommand());
            registerCommand(new SuperCommand());
            registerCommand(new LanguageCommand());
            registerCommand(new iKnowWipedCommand());
            registerCommand(new DayCommand());
            registerCommand(new SunsetCommand());
            registerCommand(new NightCommand());
            registerCommand(new MenuCommand());
            registerCommand(new ProfileCommand());
            registerCommand(new SettingsCommand());
            //Ranked Player Commands
            registerCommand(new FlyCommand());
            registerCommand(new KaboomCommand());
            registerCommand(new RideCommand());
            registerCommand(new SpeedCommand());
            registerCommand(new CoolerCommand());
            registerCommand(new FireworkCommand());
            //Substituted Commands
            registerCommand(new ListCommand());
            //registerCommand(new PluginCommand());
            registerCommand(new TeleportCommand());
            registerCommand(new RankCommand());
            registerCommand(new EmojiCommand());
        } else {
            //Substituted Commands
            registerCommand(new LanguageCommand());
            registerCommand(new WhoCommand());
            //registerCommand(new ManagerCommand());
            registerCommand(new SuperCommand());
            //registerCommand(new PluginCommand());
            //registerCommand(new TeleportCommand());
            registerCommand(new RankCommand());
            registerCommand(new EmojiCommand());
        }

        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f所有命令已成功注册!"));
    }

    private void loadScoreBoard() {
        type = this.getConfig().getString("type");
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在尝试安装计分板..."));
        Assemble assemble = new Assemble(this, new Scoreboard());
        assemble.setTicks(1);
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f计分板已成功安装!"));
    }

    private void registerListeners() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在尝试注册监听器..."));

        if (!getConfig().getBoolean("apiMode")) {
            RegisterListener.registerListeners(this, "cn.starry.hub.listener.*");
            RegisterListener.registerListeners(this, "cn.starry.hub.functions.*");
            RegisterListener.registerListeners(this, "MProtect.*");
            Bukkit.getPluginManager().registerEvents(new PlayerReceiveEvent(), this);
            Bukkit.getPluginManager().registerEvents(new CheckWipe(), this);
            Bukkit.getPluginManager().registerEvents(new CoolerCommand(), this);
            Bukkit.getPluginManager().registerEvents(new AchievementsMenu(), this);
            Bukkit.getPluginManager().registerEvents(new AchievementsSubMenu(), this);
            Bukkit.getPluginManager().registerEvents(new ChallengePageMenu(), this);
            Bukkit.getPluginManager().registerEvents(new DeliveryMenu(),this);
            Bukkit.getPluginManager().registerEvents(new FriendsMenu(),this);
            Bukkit.getPluginManager().registerEvents(new RecentPlayersMenu(),this);
            Bukkit.getPluginManager().registerEvents(new PlayerPreLoginListener(),this);
            //Bukkit.getPluginManager().registerEvents(new FunCube(), this);
            Bukkit.getPluginManager().registerEvents(new TabHandler(),this);
            Bukkit.getPluginManager().registerEvents(new PlayerProfileMenu(),this);
            Bukkit.getPluginManager().registerEvents(new PartyMenu(),this);
            Bukkit.getPluginManager().registerEvents(new LobbySettingsMenu(),this);
            Bukkit.getPluginManager().registerEvents(new ChatSettingsMenu(),this);
            Bukkit.getPluginManager().registerEvents(new CustomViewMenu(),this);
            Bukkit.getPluginManager().registerEvents(new RankColorMenu(),this);
            Bukkit.getPluginManager().registerEvents(new AgreementMenu(),this);
        } else {
            Bukkit.getPluginManager().registerEvents(new PlayerPreLoginListener(),this);
        }

        //Debug Listeners Here! Do not enable it when you are unsure of its code content
        //Bukkit.getPluginManager().registerEvents(new DebugListener(),this);

        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f所有监听器已成功注册!"));
    }

    private void loadNPCs() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在注册NPC..."));
        if (getConfig().getString("type").equalsIgnoreCase("Lobby") || getConfig().getString("type").equalsIgnoreCase("MegaWalls") || getConfig().getString("type").equalsIgnoreCase("BedWars") || getConfig().getString("type").equalsIgnoreCase("Login")) {
            new NpcFactory().init();
        }
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&fNPC已成功注册!"));
    }

    private void loadAchievements() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在注册成就..."));
        this.achievementFactory = new AchievementFactory();
        this.achievementFactory.init();
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f成就已成功注册!"));
    }

    private void loadPresents() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f正在加载礼物..."));
        if (presents) {
            this.presentsFactory = new PresentsFactory();
            this.presentsFactory.init();
            Bukkit.getPluginManager().registerEvents(new PresentsListener(), this);
        }
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f礼物已成功加载!"));
    }

    private void loadBossBar() {
        this.bossBar = new BossBarHandler();
        bossBar.init();
        Bukkit.getPluginManager().registerEvents(new BossBarHandler(),this);
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
        this.nametagHandler = new NametagHandler(this, new NameTagListener());
        this.nametagHandler.setTicks(20);
    }

    private void loadSuccess() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f版本作者 - " + team));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&f成功安装了服务器管理器"));
        if (this.getConfig().getBoolean("beta")) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&c警告! 你正在运行测试版本"));
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&c当你发现漏洞时,请反馈至此版本的作者"));
        }
        if (getConfig().getBoolean("apiMode")) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&c警告! 你正在运行api版本"));
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&c此版本不具备任何大厅功能"));
        }
    }

    private void registerCommand(Command cmd) {
        MinecraftServer.getServer().server.getCommandMap().register(cmd.getName(), this.getName(), cmd);
    }

    private void registerChannel() {
        //Bukkit.getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", new AntiWDL());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "bc:guild");
        //Register Clients - Lunar
        //Bukkit.getPluginManager().registerEvents(new LunarClient(),this);
        //new LunarClient().initClient();
    }

}
