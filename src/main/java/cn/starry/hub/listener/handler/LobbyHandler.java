package cn.starry.hub.listener.handler;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.functions.CustomSlot;
import cn.starry.hub.functions.RewardsRemind;
import cn.starry.hub.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class LobbyHandler {
    public void returnToLobby(final Player player) {
        final Location spawn = player.getWorld().getSpawnLocation();
        spawn.add(0.5, 0, 0.5);
        spawn.setYaw(Main.getPlugin(Main.class).getConfig().getInt("yaw"));
        spawn.setPitch(0);
        player.teleport(spawn);
    }

    public void firstReset(final Player player) {
        player.getInventory().setHeldItemSlot(0);
        player.setGameMode(GameMode.ADVENTURE);
    }

    public void reset(final Player player) {
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());
        player.setFallDistance(0.0f);
        player.setFoodLevel(20);
        player.setSaturation(10.0f);
        player.setFireTicks(0);
        for (final PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.getInventory().setArmorContents(null);
    }

    public void drop(final Player player) {
        player.getInventory().clear();
        PlayerData.PROFILE.put(player, ProfileState.UNLOADED);
    }

    public void load(final Player player) {
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            //player.sendMessage(ColorUtil.color("&7正在加载你的用户资料..."));
        }
        this.loadItem(player);
        CustomSlot.updateSlot(player);
        PlayerData.PROFILE.put(player, ProfileState.LOADED);
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            //player.sendMessage(ColorUtil.color("&a加载成功"));
            if (!VanishUtil.isVanish(player)) {
                if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"settings_chat_joinmessage").equals("ENABLE")) {
                    String rank = RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank"),player.getUniqueId());
                    if (player.hasPermission("lobby.premiumjoin")) {
                        String message = PlaceholderAPI.setPlaceholders(player, NickUtil.isNicked(player.getUniqueId()) ? " &b>&c>&a> " + rank + " " + NickUtil.getNickName(player.getUniqueId()) + "&6进入了大厅！ &a<&c<&b< " : " &b>&c>&a> " + rank + " %player_name% &6进入了大厅！ &a<&c<&b< ");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(ColorUtil.color(message));
                            //p.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,1);
                        }
                    } else if (player.hasPermission("lobby.rankjoin")) {
                        String message = PlaceholderAPI.setPlaceholders(player, NickUtil.isNicked(player.getUniqueId()) ? rank + " " + NickUtil.getNickName(player.getUniqueId()) + "&6进入了大厅！" : rank + " %player_name% &6进入了大厅！");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(ColorUtil.color(message));
                            //p.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,1);
                        }
                    }
                }
            }
            new RewardsRemind().sendMsg(player);
        }
    }

    public void loadMessages(final Player player) {
        if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            TitleUtil.sendTitle(player,ColorUtil.color("&f+   &bYumeGames &7; &f&l幻梦茶会   &f+"), ColorUtil.color("&8[&f1.7&7-&f1.20&8] &f小游戏群组"), 10, 20, 10);
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            TitleUtil.sendTitle(player,ColorUtil.color("&b起床战争"), ColorUtil.color("&7拿起剑,向敌人发起进攻"), 10, 20, 10);
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
            TitleUtil.sendTitle(player,ColorUtil.color("&b空岛战争"),ColorUtil.color("&7胜者为王"),10,20,10);
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("Login")) {
            TitleUtil.sendTitle(player,ColorUtil.color(""),ColorUtil.color(""),10,20,10);
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("Arcade")) {
            TitleUtil.sendTitle(player,ColorUtil.color("&街机游戏"),ColorUtil.color("&7享受快乐的游戏时光"),10,20,10);
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("MurderMystery")) {
            TitleUtil.sendTitle(player,ColorUtil.color("&b密室杀手"),ColorUtil.color("&7\"侦探在左,杀手在右\""),10,20,10);
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
            TitleUtil.sendTitle(player,ColorUtil.color("&b超级战墙"),ColorUtil.color("&7*你的恐惧一览无遗*"),10,20,10);
        } else {
            TitleUtil.sendTitle(player,ColorUtil.color("&c错误的大厅类型"),ColorUtil.color("&7请将情况反馈"),10,20,10);
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&c未知的大厅类型"));
        }
        if (Main.getPlugin(Main.class).getConfig().getString("type").equals("Lobby")) {
            player.sendMessage(ColorUtil.color("&f&m                           "));
            player.sendMessage(ColorUtil.color("&f    &bYumeGames &f幻梦茶会"));
            player.sendMessage(ColorUtil.color(""));
            player.sendMessage(ColorUtil.color("   &7群号 &f477859489"));
            player.sendMessage(ColorUtil.color("   &7地址 &fyume.games"));
            player.sendMessage(ColorUtil.color("&f&m                           "));
        }
    }

    public void loadItem(final Player player) {
        reset(player);
        CustomSlot.updateSlot(player);
        if (!Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Login")) {
            if (Objects.equals(Main.getPlugin(Main.class).getConfig().getBoolean("beta"), false)) {
                player.getInventory().setItem(1, ItemHandler.getItem(player,2));
            } else {
                player.getInventory().setItem(1, new ItemBuilder(Material.SKULL_ITEM).durability(3).name("&f个人设置 &7(右键打开)").lore("&7右键更改你已有的后缀,设置","&7以及更多内容").setSkullOwner(player.getName()).build());
            }
            player.getInventory().setItem(0, ItemHandler.getItem(player,0));
            //player.getInventory().setItem(3, ItemHandler.getItem(player,1));
            //player.getInventory().setItem(4, ItemHandler.getItem(player,6));
            player.getInventory().setItem(4, ItemHandler.getItem(player,3));
            player.getInventory().setItem(7, ItemHandler.getItem(player,4));
            player.getInventory().setItem(8, ItemHandler.getItem(player,7));
            if (!Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Lobby")) {
                player.getInventory().setItem(2, ItemHandler.getItem(player,5));
            }
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), player::updateInventory, 1L);
    }

}
