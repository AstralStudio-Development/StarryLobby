package cn.starry.hub.utils;

import org.bukkit.entity.Player;

/**
 * @Author: Stalyer
 * @Date: 2025/4/24
 */
public class TitleUtil {

    public static void sendTitle(Player player, String title, String sub, int fadeIn, int fadeOut, int duration) {
        player.sendTitle(title, sub, fadeIn, fadeOut, duration);
    }

}
