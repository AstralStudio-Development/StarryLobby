package cn.starry.hub.menus.selector;

import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum GameOwned {

    BEDWARS(0, "   &b起床战争   ", GameType.TEAM, new ItemBuilder(Material.LEGACY_BED).build(), "1.8.9", getTotalPlayer("BedWars"), Arrays.asList("   &7《起床战争》是一款极具人气的团队PVP小游戏   ","   &7核心玩法是通过团队合作一起   ","   &f收集铁锭、金锭等资源，购买装备和方块   ","   &7在&f保护己方床铺的同时，设法摧毁&7其他队伍的床   ","   &7一旦床被毁，队员将&f无法复活，战斗即刻白热化   ")),
    THEPIT(1, "   &b天坑乱斗   ", GameType.SPORTS, new ItemBuilder(Material.DIRT).build(), "1.8.9", getTotalPlayer("ThePit"), Arrays.asList("   &7《天坑乱斗》是一款快节奏的PVP小游戏   ","   &7玩家将跳入一个巨坑中，与所有人无差别战斗   ","   &7击杀对手可获得&f经验与金币，用于升级装备   ","   &7游戏还设有&f事件系统&7，为混战增添变数与策略   ","   &7节奏紧凑，非常适合追求刺激与爽快感的玩家   ")),
    DUEL(2, "   &b决斗游戏   ", GameType.SPORTS, new ItemBuilder(Material.FISHING_ROD).build(), "1.8.9", getTotalPlayer("Duel"), Arrays.asList("   &7《决斗游戏》是一项专注于PVP的玩法   ","   &7玩家可以在专门的竞技场地图中展开较量   ","   &7对玩家的&f操作技巧、走位策略&7是极大的考验   ","   &7游戏节奏紧张刺激，是检验&f个人竞技实力&7的终极舞台   ")),
    SKYWARS(3, "   &b空岛战争   ", GameType.SPORTS, new ItemBuilder(Material.LEGACY_EYE_OF_ENDER).build(), "1.8.9", getTotalPlayer("SkyWars"), Arrays.asList("   &7《空岛战争》是一款极富策略性的PVP小游戏   ","   &7玩家们各自为战或组队出生在浮岛上   ","   &7必须快速搜集岛上的宝箱获取资源   ","   &7并搭路前往资源更丰富的&f中心岛屿   ","   &f利用地形、技巧和搜刮到的装备&7，与其他玩家交战   ","   &7瞬息万变的战局，带来了紧张刺激的竞技体验   ")),
    ARCADE(4, "   &b街机游戏   ", GameType.LEISURE, new ItemBuilder(Material.SLIME_BALL).build(), "1.8.9", getTotalPlayer("Arcade"), Arrays.asList("   &7《街机游戏》中包含了各种各样的娱乐小游戏   ", "   &7你可以体验到多种不同风格的趣味游戏   ","   &7无论是发挥创意，还是考验反应速度   ","   &7都能让你在短时间内获得纯粹的欢乐   ")),
    RPG(5, "   &b羁行之旅   ", GameType.RPG, new ItemBuilder(Material.BOOK).build(), "1.12.2", getTotalPlayer("RPG"), Arrays.asList("    ")),
    //SKYBLOCK(7, "   &b空岛生存  ", GameType.RPG, SkullUtil.makeTextureSkull("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdjYzY2ODc0MjNkMDU3MGQ1NTZhYzUzZTA2NzZjYjU2M2JiZGQ5NzE3Y2Q4MjY5YmRlYmVkNmY2ZDRlN2JmOCJ9fX0="), "1.12.2", getTotalPlayer("RPG"), Arrays.asList("  &7空岛生存终于降临于YumeGames服务器  ", "  &7单人生存或召集伙伴，建造你的  ", "  &7私人岛屿，收集万千资源吧  ")),
    MURDERMYSTERY(8, "   &b密室杀手   ", GameType.LEISURE, new ItemBuilder(Material.BOW).build(), "1.8.9", getTotalPlayer("MurderMystery"), Arrays.asList("   &7《密室杀手》一款极具悬疑要素的推理小游戏   ","   &7玩家被随机分配为杀手、侦探或平民三种角色   ","   &7杀手需暗中用刀消灭对手，侦探手持弓箭，负责制裁杀手   ","   &7而平民则需收集金锭换取弓箭来反击   ","   &7游戏核心在于身份隐藏、心理博弈，充满推理乐趣   ")),
    HOUSING(9, "   &b家园世界   ", GameType.SURVIVAL, new ItemBuilder(Material.LEGACY_DARK_OAK_DOOR_ITEM).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("  &7建筑并装饰属于你自己的  ", "  &7专属领地，邀请朋友们做客，  ", "  &7参观其他玩家的建筑，  ", "  &7以及更多不尽言传的乐趣！  ")),
    BUILDBATTLE(10, "   &b建筑大师   ", GameType.SURVIVAL, new ItemBuilder(Material.LEGACY_WORKBENCH).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("  &7在5分钟之内根据出题  ", "  &7建造一个建筑！用“传奇屎诗”到  ", "  &7“传奇”的不同程度评价  ", "  &7对建成的建筑进行投票！  ", "  &7在16名玩家中脱颖而出，取得胜利吧！  ")),
    UHC(11, "   &b极限生存冠军   ", GameType.SURVIVAL, new ItemBuilder(Material.GOLDEN_APPLE).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("  &7极限生存游戏， 在原版竞技的基础上，  ", "  &7新增了合成、增益效果、职业等内容。  ")),
    MEGAWALLS(12, "   &b超级战墙   ", GameType.SURVIVAL, new ItemBuilder(Material.SOUL_SAND).build(), "1.12.2", getTotalPlayer("SkySurvival"), Arrays.asList("  &7一款史诗级的百人游戏，  ", "  &7带有趣味和独特的职业。  ", "  &7击败敌人的凋零，  ", "  &7以防止你的敌人重生。  ")),
    PROTOTYPE(13, "  &b游戏实验室   ", GameType.LEISURE, new ItemBuilder(Material.ANVIL).build(), "1.12.2", getTotalPlayer("Prototype"), Arrays.asList("  &7游戏实验室是个测试并  ", "  &7创造欢乐与游戏的地方。  ", "  &7  ", "  &f• 击退战争  ", "  &7  ", "  &c在这个大厅里的所有项目现  ", "  &c都还处于开发阶段，随时  ", "  &c都有可能被移除  "));


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
