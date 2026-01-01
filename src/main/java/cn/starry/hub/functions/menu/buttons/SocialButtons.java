package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.Core;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.LuckPermsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SocialButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack noFriends() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7你可以点击上方的“添加好友” ");
        lores.add(" &7或&b/friend add 玩家名");
        lores.add(" &7来添加好友！");
        lores.add(" ");

        item = new ItemBuilder(Material.GLASS_BOTTLE).name(CC.translate(" &c你还木有好友(⌯꒪꒫꒪)੭")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack friendSkullItem(String name) {
        List<String> lores = new ArrayList<>();
            /*
        AllData data = new AllData();
        lores.add("&7成就点数 &e" + Main.getInstance().getData().getAchievementPoints(UUID.fromString(Main.getInstance().getData().getPlayerData(name,"uuid")),"points"));
        UUID playerUUID = UUID.fromString(Main.getInstance().getData().getPlayerData(name, "uuid"));

             */
        /*
        if (data.hasGuid(playerUUID)) {
            String guildUUID = data.getGuild(playerUUID);
            String guildName = data.getGName(guildUUID);
            lores.add("&7公会 &b" + (guildName != null ? guildName : "无"));
        }

         */
        lores.add("");
        lores.add("&7状态 " + (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(name)) ? "&b在线" : "&c离线"));

        if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(name))) {
            Player player = Bukkit.getPlayer(name);
            UUID uuid = Bukkit.getPlayer(name).getUniqueId();
            item = new ItemBuilder(Material.LEGACY_SKULL_ITEM).durability(3).name(CC.translate(LuckPermsUtil.getPlayerRank(uuid) + name)).setModernSkullOwner(player).lore(lores).build();
        } else {
            UUID uuid = UUID.fromString(Core.getInstance().getMongoDB().getPlayerData(name,"uuid"));
            item = new ItemBuilder(Material.LEGACY_SKULL_ITEM).durability(3).name(CC.translate(LuckPermsUtil.getPlayerRankByCache(uuid)+ name)).lore(lores).build();
            item = ItemBuilder.itemUuid(item,uuid);
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack friendAddItem() {
        List<String> lores = new ArrayList<>();
        lores.add("&7点击以添加一个好友到好友列表中");
        lores.add("");
        lores.add("&7好友除了可以互相查看对方正在服务器上做什么");
        lores.add("&7还可以互相查看对方是否在线");

        item = new ItemBuilder(Material.LEGACY_BOOK_AND_QUILL).name(CC.translate("&a添加好友")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack friendRemoveItem() {
        List<String> lores = new ArrayList<>();
        lores.add("&7点击此处从你当前的好友列表中删除好友");

        item = new ItemBuilder(Material.BARRIER).name(CC.translate("&c删除好友")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack noParty() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add("&e点击此处, 邀请玩家加入你的组队");

        item = new ItemBuilder(Material.LEGACY_STAINED_CLAY).durability(2).name(CC.translate("&a创建一个组队")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack inviteParty() {
        List<String> lores = new ArrayList<>();
        lores.add("&7邀请玩家加入你的组队。");

        item = new ItemBuilder(Material.LEGACY_BOOK_AND_QUILL).name(CC.translate("&a邀请玩家")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack removeParty() {
        List<String> lores = new ArrayList<>();
        lores.add("&7从队伍中移除一名玩家。");

        item = new ItemBuilder(Material.BARRIER).name(CC.translate("&a删除玩家")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack warpParty() {
        List<String> lores = new ArrayList<>();
        lores.add("&7传送所有组队成员至你的大厅。");

        item = new ItemBuilder(Material.LEGACY_NETHER_BRICK_ITEM).name(CC.translate("&a传送组队")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack disbandParty() {
        List<String> lores = new ArrayList<>();
        lores.add("&7解散当前的组队。");

        item = new ItemBuilder(Material.TNT).name(CC.translate("&a解散组队")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack partySkullItem(String name) {
        List<String> lores = new ArrayList<>();
        /*
        lores.add("&7成就点数 &e" + CacheData.achievement_points.get().getAchievementPoints(UUID.fromString(Main.getInstance().getData().getPlayerData(name,"uuid")),"points"));

        if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(name))) {
            Player player = Bukkit.getPlayer(name);
            UUID uuid = Bukkit.getPlayer(name).getUniqueId();
            item = new ItemBuilder(Material.LEGACY_SKULL_ITEM).durability(3).name(CC.translate(LuckPermsUtil.getPlayerRank(uuid) + name)).setModernSkullOwner(player).lore(lores).build();
        } else {
            UUID uuid = UUID.fromString(Main.getInstance().getData().getPlayerData(name,"uuid"));
            item = new ItemBuilder(Material.LEGACY_SKULL_ITEM).durability(3).name(CC.translate(LuckPermsUtil.getPlayerRankByCache(uuid)+ name)).lore(lores).build();
            item = ItemBuilder.itemUuid(item,uuid);
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;

         */
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack clearRecentPlayers() {
        List<String> lores = new ArrayList<>();
        lores.add("&7清除近期活跃玩家列表。");

        item = new ItemBuilder(Material.BARRIER).name(CC.translate("&a清除近期活跃玩家")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack noRecentPlayers() {
        List<String> lores = new ArrayList<>();
        lores.add("&7在服务器上游玩游戏,");
        lores.add("&7然后回到这里看看,");
        lores.add("&7有哪些玩家与你一起游玩！");

        item = new ItemBuilder(Material.GLASS_BOTTLE).name(CC.translate("&c未发现近期活跃玩家")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
