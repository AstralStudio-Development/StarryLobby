package cn.starry.hub.utils;

import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.listeners.handler.LobbyHandler;
import java.util.Objects;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author: Starry_Killer
 * @Date: 2023/11/28
 */
public class StoreUtil {
    public static void buy(Player player, int points, int require, String cmd1, String cmd2, String cmd3, String subMsg, boolean isRank) {
        if (player.getInventory() != null) {
            player.closeInventory();
        }
        player.getInventory().clear();
        if (points < require) {
            TitleUtil.sendTitle(player, CC.translate("&c错误"), CC.translate("&f没有足够的点券"), 20, 20, 20);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
            new LobbyHandler().loadItem(player);
        } else {
            TitleUtil.sendTitle(player, CC.translate("&b获取成功"), CC.translate(subMsg), 20, 20, 20);
            if (isRank) {
                AchievementManager.unlockAchievement(player, AchievementManager.getAchievement("VIP"));
            }
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("p take " + player.getName() + " " + require));
            if (!Objects.equals(cmd1, "")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)PlaceholderAPI.setPlaceholders((Player)player, (String)cmd1));
            }
            if (!Objects.equals(cmd2, "")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)PlaceholderAPI.setPlaceholders((Player)player, (String)cmd2));
            }
            if (!Objects.equals(cmd3, "")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)PlaceholderAPI.setPlaceholders((Player)player, (String)cmd3));
            }
            new LobbyHandler().loadItem(player);
        }
    }
}
