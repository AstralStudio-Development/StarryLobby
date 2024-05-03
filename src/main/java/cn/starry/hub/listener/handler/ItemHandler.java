package cn.starry.hub.listener.handler;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemHandler {
    public static ItemStack SELECTOR;
    public static ItemStack STORE;
    public static ItemStack PROFILE;
    public static ItemStack GADGETSMENU;
    public static ItemStack SETTINGS;
    public static ItemStack COSMETICS;
    public static ItemStack PROTOTYPE;
    public static ItemStack LOBBYS;

    public static ItemStack getItem(Player player,int id) {
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            /*
            case ENGLISH:
                ItemHandler.SELECTOR = new ItemBuilder(Material.PAPER).name("&eGame Menu &7(Right Click)").lore("&7Right-Click to bring up the Game Menu").build();
                ItemHandler.STORE = new ItemBuilder(Material.NAME_TAG).name("&fStore &7(Right Click)").lore("&7Right-Click to open Server Store", "&7Can be used to purchase Suffix,Rank,etc.").build();
                ItemHandler.PROFILE = new ItemBuilder(Material.SKULL_ITEM).durability(3).name("&fMy Profile &7(Right Click)").lore("&7Right-Click to Change your existing suffix,Settings","&7and more content").setNewSkullOwner(player).build();
                ItemHandler.PROTOTYPE = new ItemBuilder(Material.ANVIL).name("&fPrototype &7(Right Click)").lore("&7右键打开铸造厂投票菜单","&7选择你希望上线的游戏").build();
                ItemHandler.GADGETSMENU = new ItemBuilder(Material.CHEST).name("&fCollectibles &7(Right Click)").lore("&7Right-Click to open Collectibles Menu").build();
                ItemHandler.SETTINGS = new ItemBuilder(Material.FEATHER).name("&fSettings &7(Right Click)").lore("&7Right-Click to open Settings Menu", "&7Use this to customize your visual experience").build();
                ItemHandler.COSMETICS = new ItemBuilder(Material.EMERALD).name("&fCosmetics & Shop &7(Right Click)").lore("&7Right-Click to open Game Store").build();
                break;
            case JAPANESE:
                ItemHandler.SELECTOR = new ItemBuilder(Material.PAPER).name("&eゲームメニュー &7(右クリック)").lore("&7Right-Click to bring up the Game Menu").build();
                ItemHandler.STORE = new ItemBuilder(Material.NAME_TAG).name("&fショップ &7(右クリック)").lore("&7Right-Click to open Server Store", "&7Can be used to purchase Suffix,Rank,etc.").build();
                ItemHandler.PROFILE = new ItemBuilder(Material.SKULL_ITEM).durability(3).name("&fプロフィール &7(右クリック)").lore("&7Right-Click to Change your existing suffix,Settings","&7and more content").setNewSkullOwner(player).build();
                ItemHandler.PROTOTYPE = new ItemBuilder(Material.ANVIL).name("&fテストされたゲーム  &7(右クリック)").lore("&7右键打开铸造厂投票菜单","&7选择你希望上线的游戏").build();
                ItemHandler.GADGETSMENU = new ItemBuilder(Material.CHEST).name("&f収集品 &7(右クリック)").lore("&7Right-Click to open Collectibles Menu").build();
                ItemHandler.SETTINGS = new ItemBuilder(Material.FEATHER).name("&f設定 &7(右クリック)").lore("&7Right-Click to open Settings Menu", "&7Use this to customize your visual experience").build();
                ItemHandler.COSMETICS = new ItemBuilder(Material.EMERALD).name("&fCosmetics & Shop &7(右クリック)").lore("&7Right-Click to open Game Store").build();
                break;

             */
            default:
                ItemHandler.SELECTOR = new ItemBuilder(Material.COMPASS).name("&a游戏菜单 &7(右键打开)").lore("&7右键打开游戏菜单").build();
                ItemHandler.STORE = new ItemBuilder(Material.NAME_TAG).name("&f货摊 &7(右键打开)").lore("&7右键打开货摊菜单", "&7可用这个购买服务器物品").build();
                ItemHandler.PROFILE = new ItemBuilder(Material.SKULL_ITEM).durability(3).name("&a个人档案 &7(右键打开)").lore("&7右键更改你已有的后缀,设置","&7以及更多内容").setNewSkullOwner(player).build();
                ItemHandler.PROTOTYPE = new ItemBuilder(Material.ANVIL).name("&a铸造厂 &7(右键打开)").lore("&7右键打开铸造厂投票菜单","&7选择你希望上线的游戏").build();
                ItemHandler.GADGETSMENU = new ItemBuilder(Material.CHEST).name("&a收藏品 &7(右键点击)").lore("&7右键打开收藏品菜单").build();
                ItemHandler.SETTINGS = new ItemBuilder(Material.SIGN).name("&a设置 &7(右键打开)").lore("&7右键打开设置菜单", "&7可用这个自定义你的视觉").build();
                ItemHandler.COSMETICS = new ItemBuilder(Material.EMERALD).name("&a商店与特效 &7(右键打开)").lore("&7右键打开该游戏的商店菜单").build();
                ItemHandler.LOBBYS = new ItemBuilder(Material.NETHER_STAR).name("&a选择大厅 &7(右键打开)").lore("&7右键打开以在不同大厅间切换", "&7可用这个与你的朋友待在一起").build();
        }
        if (id == 0) {
            return SELECTOR;
        } else if (id == 1) {
            return STORE;
        } else if (id == 2) {
            return PROFILE;
        } else if (id == 3) {
            return GADGETSMENU;
        } else if (id == 4) {
            return SETTINGS;
        } else if (id == 5) {
            return COSMETICS;
        } else if (id == 6) {
            return PROTOTYPE;
        } else if (id == 7) {
            return LOBBYS;
        } else {
            return null;
        }
    }
}
