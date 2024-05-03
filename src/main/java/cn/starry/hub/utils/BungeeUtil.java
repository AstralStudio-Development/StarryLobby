package cn.starry.hub.utils;

import cn.starry.hub.api.data.CacheData;
import cn.starry.hub.Main;
import cn.starry.hub.functions.achievement.AchievementManager;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * @Author: Starry_Killer
 * @Date: 2023/06/23
 */
public class BungeeUtil {

    public static void sendServer(final Player player, final String server) {
        if (player.getInventory() != null) {
            player.closeInventory();
        }
        if (CacheData.QUEUE.get(player).equals(true)) {
            player.sendMessage(ColorUtil.color("&c你目前已经处于匹配队列中,无法再次匹配！"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
            return;
        }
        CacheData.QUEUE.put(player, true);
        if (Main.getPlugin(Main.class).isProxy()) {
            AchievementManager.unlockAchievement(player,AchievementManager.getAchievement("WhereWillIGo"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spb connect " + server + " " + player.getDisplayName());
        } else {
            connect(player,server);
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
            if (player.isOnline()) {
                CacheData.QUEUE.put(player, false);
            }
        }, 20L);
    }

    public static void connect(Player player, String server) {
        AchievementManager.unlockAchievement(player,AchievementManager.getAchievement("WhereWillIGo"));
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream o = new DataOutputStream(b);
        try {
            o.writeUTF("Connect");
            o.writeUTF(server);
            player.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", b.toByteArray());
        }
        catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§c无法连接至BungeeCord,请检查spigot.yml");
        }
    }

    public static void sendFriendCmd(Player player, String cmd) {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        Server s = Bukkit.getPlayer(player.getName()).getServer();
        packet.writeUTF("CMD-Friend");
        packet.writeUTF(player.getUniqueId().toString());
        packet.writeUTF(cmd);
        s.sendPluginMessage(Main.getInstance(), "bc:guild", packet.toByteArray());
    }

    public static void sendPartyCmd(Player player, String cmd) {
        ByteArrayDataOutput packet = ByteStreams.newDataOutput();
        Server s = Bukkit.getPlayer(player.getName()).getServer();
        packet.writeUTF("CMD-Party");
        packet.writeUTF(player.getUniqueId().toString());
        packet.writeUTF(cmd);
        s.sendPluginMessage(Main.getInstance(), "bc:guild", packet.toByteArray());
    }

}
