package cn.starry.hub.listener.handler;

import cn.starry.core.Core;
import cn.starry.core.api.enums.Permission;
import cn.starry.core.utils.*;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.CustomSlot;
import cn.starry.hub.utils.TitleUtil;
import dev.jnic.annotations.Include;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

@Include
public class LobbyHandler {

    public void returnToLobby(final Player player) {
        final Location spawn = player.getWorld().getSpawnLocation();
        spawn.add(0.5, 0, 0.5);
        spawn.setYaw(StarryLobby.getPlugin(StarryLobby.class).getConfig().getInt("yaw"));
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
    }

    public void load(final Player player) {
        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            //player.sendMessage(CC.translate("&7正在加载你的用户资料..."));
        }
        this.loadItem(player);
        //CustomSlot.updateSlot(player);
        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            if (!VanishUtils.isVanish(player)) {
                //player.sendMessage(CC.translate("&a加载成功"));
                if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(), "settings_chat_joinmessage").equalsIgnoreCase("TRUE")) {
                    String rank = RankUtil.getFormatRankById(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(), "rank"), player.getUniqueId());
                    if (player.hasPermission(Permission.PREMIUM.getNode())) {
                        String message = PlaceholderAPI.setPlaceholders(player, NickUtil.isNicked(player.getUniqueId()) ? "&6  + " + rank + " " + NickUtil.getNickName(player.getUniqueId()) + "&7踏入了琉光艺庭！" : "&6  + " + rank + " %player_name% &7踏入了琉光艺庭！");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(CC.translate(message));
                            p.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        }
                    } else if (player.hasPermission(Permission.RANK.getNode())) {
                        String message = PlaceholderAPI.setPlaceholders(player, NickUtil.isNicked(player.getUniqueId()) ? "&a  + " + rank + " " + NickUtil.getNickName(player.getUniqueId()) + "&7踏入了琉光艺庭！" : "&a  + " + rank + " %player_name% &7踏入了琉光艺庭！");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(CC.translate(message));
                            p.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        }
                    }
                }
            }
        }
    }

    public void loadMessages(final Player player) {
        if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            TitleUtil.sendTitle(player, CC.translate("&f+   &bYumeGames &7; &f&l幻梦茶会   &f+"), CC.translate("&8[&f1.7&7-&f1.21&8] &f小游戏群组"), 10, 20, 10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            TitleUtil.sendTitle(player,CC.translate("&b起床战争"), CC.translate("&7拿起剑,向敌人发起进攻"), 10, 20, 10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
            TitleUtil.sendTitle(player,CC.translate("&b空岛战争"),CC.translate("&7胜者为王"),10,20,10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Login")) {
            TitleUtil.sendTitle(player,CC.translate(""),CC.translate(""),10,20,10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Arcade")) {
            TitleUtil.sendTitle(player,CC.translate("&街机游戏"),CC.translate("&7享受快乐的游戏时光"),10,20,10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("MurderMystery")) {
            TitleUtil.sendTitle(player,CC.translate("&b密室杀手"),CC.translate("&7\"侦探在左,杀手在右\""),10,20,10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
            TitleUtil.sendTitle(player,CC.translate("&b超级战墙"),CC.translate("&7*你的恐惧一览无遗*"),10,20,10);
        } else if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Prototype")) {
            TitleUtil.sendTitle(player,CC.translate("&b超级战墙"),CC.translate("&7Ciallo～(∠・ω< )⌒★"),10,20,10);
        } else {
            TitleUtil.sendTitle(player,CC.translate("&c错误的大厅类型"),CC.translate("&7请将情况反馈"),10,20,10);
            Bukkit.getConsoleSender().sendMessage(CC.translate("&c未知的大厅类型"));
        }
        if (StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type").equals("Lobby")) {
            player.sendMessage(CC.translate(" "));
            player.sendMessage(CC.translate("&b  &f&l幻梦茶会 &8«"));
            player.sendMessage(CC.translate(" "));
            player.sendMessage(CC.translate("&7  欢迎 " + RankUtil.getFormatRankById(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rank"), player.getUniqueId()) + " " + player.getDisplayName() + " &7进入服务器"));
            player.sendMessage(CC.translate(" "));
            player.sendMessage(CC.translate("&8  » &7交流群 &f477859489"));
            player.sendMessage(CC.translate("&8  » &7游戏地址 &fYume.games"));
            player.sendMessage(CC.translate(" "));
        }
    }

    public void loadItem(final Player player) {
        reset(player);
        //CustomSlot.updateSlot(player);
        if (!StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type").equalsIgnoreCase("Login")) {
            player.getInventory().setItem(2, ItemHandler.getItem(player,2));
            player.getInventory().setItem(0, ItemHandler.getItem(player,0));
            player.getInventory().setItem(7, ItemHandler.getItem(player,3));
            player.getInventory().setItem(8, ItemHandler.getItem(player,4));
            if (!StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Lobby")) {
                if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Prototype")) {
                    player.getInventory().setItem(3, ItemHandler.getItem(player,6));
                } else {
                    player.getInventory().setItem(3, ItemHandler.getItem(player, 5));
                }
            }
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(StarryLobby.getPlugin(StarryLobby.class), player::updateInventory, 1L);
    }

}
