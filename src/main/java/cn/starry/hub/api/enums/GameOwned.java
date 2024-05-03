package cn.starry.hub.api.enums;

import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.SkullUtil;
import cn.starry.hub.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum GameOwned {

    BEDWARS(0, "&a起床战争 &e&l版本更新", GameType.TEAM, new ItemBuilder(Material.BED).build(), "1.8.9", getTotalPlayer("BedWars"), Arrays.asList("&7与队友一起保护你们的床，并摧毁敌人的床以取胜！")),
    THEPIT(1, "&a天坑乱斗", GameType.SPORTS, new ItemBuilder(Material.DIRT).build(), "1.8.9", getTotalPlayer("ThePit"), Arrays.asList("&7跳入天坑与多名玩家实时对决。", "&7YumeGames中节奏最快的PvP游戏。", "", "&c前方高能！", "&c你已进入极限区域，", "&c不推荐新手游玩")),
    DUEL(2, "&a决斗游戏", GameType.SPORTS, new ItemBuilder(Material.FISHING_ROD).build(), "1.8.9", getTotalPlayer("Duel"), Arrays.asList("&7快节奏的1v1，2v2或4v4！")),
    SKYWARS(3, "&a空岛战争", GameType.SPORTS, new ItemBuilder(Material.EYE_OF_ENDER).build(), "1.8.9", getTotalPlayer("SkyWars"), Arrays.asList("&7YumeGames独特玩法的空岛战争。", "&7其特色包括死亡使者，灵魂井", "&7以及&c疯狂模式！", "&7单人或组队，任君挑选")),
    ARCADE(4, "&a街机游戏 &d&l漏洞修复补丁推送 & 新地图", GameType.LEISURE, new ItemBuilder(Material.SLIME_BALL).build(), "1.8.9", getTotalPlayer("Arcade"), Arrays.asList("&7刺激有趣的街机小游戏，", "&7与小伙伴们一同开玩吧")),
    RPG(5, "&a幻梦双笙:羁行之旅", GameType.RPG, new ItemBuilder(Material.BOOK).build(), "1.12.2", getTotalPlayer("RPG"), Arrays.asList("&7准备好踏入冒险了吗？")),
    SKYBLOCK(7, "&a空岛生存", GameType.RPG, SkullUtil.makeTextureSkull("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdjYzY2ODc0MjNkMDU3MGQ1NTZhYzUzZTA2NzZjYjU2M2JiZGQ5NzE3Y2Q4MjY5YmRlYmVkNmY2ZDRlN2JmOCJ9fX0="), "1.12.2", getTotalPlayer("RPG"), Arrays.asList("&7空岛生存终于降临于YumeGames服务器", "&7单人生存或召集伙伴，建造你的", "&7私人岛屿，收集万千资源吧")),
    MURDERMYSTERY(8, "&a密室杀手", GameType.LEISURE, new ItemBuilder(Material.BOW).build(), "1.8.9", getTotalPlayer("MurderMystery"), Arrays.asList("&7一名杀手，一名侦探。", "&7还有一群平民群众。", "&7你能在这场危机四伏", "&7的背叛与谋杀中幸存吗？")),
    HOUSING(9, "&a家园世界 &6&l更新！", GameType.SURVIVAL, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("&7建筑并装饰属于你自己的", "&7专属领地，邀请朋友们做客，", "&7参观其他玩家的建筑，", "&7以及更多不尽言传的乐趣！")),
    BUILDBATTLE(10, "&a建筑大师 &d&l漏洞修复补丁推送", GameType.SURVIVAL, new ItemBuilder(Material.WORKBENCH).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("&7在5分钟之内根据出题", "&7建造一个建筑！用“传奇屎诗”到", "&7“传奇”的不同程度评价", "&7对建成的建筑进行投票！", "&7在16名玩家中脱颖而出，取得胜利吧！")),
    UHC(11, "&a极限生存冠军", GameType.SURVIVAL, new ItemBuilder(Material.GOLDEN_APPLE).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("&7极限生存游戏， 在原版PvP的基础上，", "&7新增了合成、增益效果、职业等内容。", "", "&c前方高能！", "&c你已进入极限区域，", "&c不推荐新手游玩")),
    MEGAWALLS(12, "&a超级战墙", GameType.SURVIVAL, new ItemBuilder(Material.SOUL_SAND).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("&7一款史诗级的百人游戏，", "&7带有趣味和独特的职业。", "&7击败敌人的凋零，", "&7以防止你的敌人重生。")),
    PROTOTYPE(13, "&a游戏实验室", GameType.LEISURE, new ItemBuilder(Material.ANVIL).build(), "1.12.2", getTotalPlayer("Prototype"), Arrays.asList("&7游戏实验室是个测试并", "&7创造欢乐与游戏的地方。", "&7", "&c在这个大厅里的所有项目现", "&c都还处于开发阶段，随时", "&c都有可能被移除"));


    private final int id;
    private final String displayName;
    private final GameType gameType;
    private final ItemStack itemStack;
    private final String version;
    private final String onlinePapi;
    private final List<String> description;

    private GameOwned(int id, String displayName, GameType gameType, ItemStack itemStack, String version, String onlinePapi, List<String> description) {
        this.id = id;
        this.displayName = displayName;
        this.gameType = gameType;
        this.itemStack = itemStack;
        this.version = version;
        this.onlinePapi = onlinePapi;
        this.description = description;
    }

    public int getID() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public GameType getType() {
        return this.gameType;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public String getVersion() {
        return this.version;
    }

    public String getOnlinePapi() {
        return this.onlinePapi;
    }

    public List<String> getDescription() {
        return this.description;
    }

    private static String getTotalPlayer(String server) {
        return "0";
    }

}
