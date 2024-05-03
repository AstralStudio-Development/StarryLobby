package cn.starry.hub.utils;

import cn.starry.hub.functions.achievement.AchievementManager;
import cn.starry.hub.listener.handler.LobbyHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/28
 */
public class StoreUtil {

    public static void buy(Player player,int points,int require, String cmd1, String cmd2, String cmd3,String subMsg,boolean isRank) {
        if (player.getInventory() != null) {
            player.closeInventory();
        }
        player.getInventory().clear();
        if (points < require) {
            TitleUtil.sendTitle(player, ColorUtil.color("&c错误"), ColorUtil.color("&f没有足够的点券"),20,20,20);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            new LobbyHandler().loadItem(player);
        } else {
            TitleUtil.sendTitle(player,ColorUtil.color("&b获取成功"), ColorUtil.color(subMsg), 20, 20, 20);
            if (isRank) {
                AchievementManager.unlockAchievement(player,AchievementManager.getAchievement("VIP"));
            }
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "p take " + player.getName() + " " + require);
            if (!Objects.equals(cmd1, "")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player,cmd1));
            }
            if (!Objects.equals(cmd2, "")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player,cmd2));
            }
            if (!Objects.equals(cmd3, "")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player,cmd3));
            }
            new LobbyHandler().loadItem(player);
        }
    }

}
