package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SettingButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack ScreenButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7当前类型 &a" + Main.getPlugin(Main.class).getData().getPlayerData(player.getUniqueId(),"screen")
                .replace("ALL","所有游戏")
                .replace("SPORTS","仅竞技类游戏")
                .replace("LEISURE","仅休闲类游戏")
                .replace("SURVIVAL","仅生存类游戏")
                .replace("RPGGAME","仅RPG类游戏")
                .replace("PRACTICE","仅练习类游戏"));
        lores.add(" ");
        lores.add("&7下一类型 &a" + Main.getPlugin(Main.class).getData().getPlayerData(player.getUniqueId(),"screen")
                .replace("ALL","仅竞技类游戏")
                .replace("SPORTS","仅休闲类游戏")
                .replace("LEISURE","仅生存类游戏")
                .replace("SURVIVAL","仅RPG类游戏")
                .replace("RPGGAME","仅练习类游戏")
                .replace("PRACTICE","所有游戏"));
        lores.add("&e左键切换");
        lores.add(" ");

        item = new ItemBuilder(Material.HOPPER)
                .name(ColorUtil.color("&6筛选 &a" + Main.getPlugin(Main.class).getData().getPlayerData(player.getUniqueId(),"screen")
                        .replace("ALL","所有游戏")
                        .replace("SPORTS","仅竞技类游戏")
                        .replace("LEISURE","仅休闲类游戏")
                        .replace("SURVIVAL","仅生存类游戏")
                        .replace("RPGGAME","仅RPG类游戏")
                        .replace("PRACTICE","仅练习类游戏")))
                .lore(lores)
                .build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_ITEM(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        lores.add("&7设置玩家是否可见。");

        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("ENABLE")) {
            item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color("&a玩家可见性")).lore(lores).build();
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("DISABLE")) {
            item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color("&c玩家可见性")).lore(lores).build();
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("RANK")) {
            item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color("&b玩家可见性")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_ENABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7选项:");
        lores.add("&a➟ &a显示所有玩家");
        lores.add("   &c隐藏所有玩家");
        lores.add("   &b仅会员及以上");
        lores.add("");
        lores.add("&e点击改为&c隐藏所有玩家&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a玩家可见性")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_DISABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7选项:");
        lores.add("   &a显示所有玩家");
        lores.add("&c➟ &c隐藏所有玩家");
        lores.add("   &b仅会员及以上");
        lores.add("");
        lores.add("&e点击改为&b仅会员及以上&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&a玩家可见性")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_RANK() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7选项:");
        lores.add("   &a显示所有玩家");
        lores.add("   &c隐藏所有玩家");
        lores.add("&b➟ &b仅会员及以上");
        lores.add("");
        lores.add("&e点击改为&a显示所有玩家&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(12).name(ColorUtil.color("&a玩家可见性")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RECEIVE_ITEM(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        lores.add("&7设置聊天对话是否可见。");
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("ENABLE")) {
            item = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&a聊天可见性")).lore(lores).build();
        } else {
            item = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&c聊天可见性")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RECEIVE_ENABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("&7点击以禁用！");

        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a显示玩家消息")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RECEIVE_DISABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("&7点击以启用！");

        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&c显示玩家消息")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_ITEM(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        lores.add("&7设置大厅以白天/傍晚/夜晚对你显示。");
        item = new ItemBuilder(Material.WATCH).name(ColorUtil.color("&a大厅时间状态")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_DAY() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7选项:");
        lores.add("&a➟ &a白天");
        lores.add("   &6傍晚");
        lores.add("   &8夜晚");
        lores.add("");
        lores.add("&e点击改为&6傍晚&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(1).name(ColorUtil.color("&a大厅时间状态")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_SUNSET() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7选项:");
        lores.add("   &a白天");
        lores.add("&6➟ &6傍晚");
        lores.add("   &8夜晚");
        lores.add("");
        lores.add("&e点击改为&8夜晚&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(14).name(ColorUtil.color("&a大厅时间状态")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_NIGHT() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7选项:");
        lores.add("   &a白天");
        lores.add("   &6傍晚");
        lores.add("&8➟ &8夜晚");
        lores.add("");
        lores.add("&e点击改为&a白天&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(0).name(ColorUtil.color("&a大厅时间状态")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至个人档案");

        item = new ItemBuilder(Material.ARROW).name(ColorUtil.color("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack GlassButton(Player player,int durability) {
        List<String> lores = new ArrayList<>();
        lores.add("&8⇩ &7设置");
        item = new ItemBuilder(Material.STAINED_GLASS_PANE).name(ColorUtil.color("&8⇧ &7类别")).durability(durability).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack LobbySettingsButton(Player player,boolean in) {
        List<String> lores = new ArrayList<>();
        lores.add("&e点击浏览！");
        if (in) {
            item = new ItemBuilder(Material.NETHER_STAR).name(ColorUtil.color("&a大厅设置")).build();
        } else {
            item = new ItemBuilder(Material.NETHER_STAR).name(ColorUtil.color("&a大厅设置")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ChatSettingsButton(Player player,boolean in) {
        List<String> lores = new ArrayList<>();
        lores.add("&e点击浏览！");
        if (in) {
            item = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&a聊天设置")).build();
        } else {
            item = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&a聊天设置")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack JOIN_MESSAGE_ITEM(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        lores.add("&7设置是否在你加入大厅的时候发送加入消息。");
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("ENABLE")) {
            item = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&a发送大厅加入消息")).lore(lores).build();
        } else {
            item = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&c发送大厅加入消息")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack JOIN_MESSAGE_ENABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("&7点击以禁用！");
        lores.add("");
        lores.add("&7需要&bMVP&c+");

        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a发送大厅加入消息")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack JOIN_MESSAGE_DISABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("&7点击以启用！");
        lores.add("");
        lores.add("&7需要&bMVP&c+");

        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&c发送大厅加入消息")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack FILTERATE_PUBLIC_ITEM(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("ENABLE")) {
            lores.add("&7大多数被认为是不合适的消息将会被自动筛选。");
            item = new ItemBuilder(Material.ANVIL).name(ColorUtil.color("&a公屏过滤等级")).lore(lores).build();
        } else {
            lores.add("&7你将能够看到可能不合适的消息。");
            item = new ItemBuilder(Material.ANVIL).name(ColorUtil.color("&a公屏过滤等级")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack FILTERATE_PUBLIC_ENABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7大多数被认为是不合适的消息将会被自动筛选。");
        lores.add("");
        lores.add("&7选项:");
        lores.add("&a➟ &a开");
        lores.add("   &c关");
        lores.add("");
        lores.add("&e点击改为&c关&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a公屏过滤等级")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack FILTERATE_PUBLIC_DISABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7你将能够看到可能不合适的消息。");
        lores.add("");
        lores.add("&7选项:");
        lores.add("   &a开");
        lores.add("&c➟ &c关");
        lores.add("");
        lores.add("&e点击改为&a开&e！");

        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&c公屏过滤等级")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
