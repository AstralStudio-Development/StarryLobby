package cn.starry.hub.functions.activity.springfestival.Y2024;

import cn.starry.hub.functions.achievement.AchievementManager;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.TitleUtil;
import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Y2024Major {

    private int counter = 0;

    public void startEvent() {
        // 向所有在线玩家发送消息
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            TitleUtil.sendTitle(player,"&c&k1&c新年快乐&c&k1","",10,10,10);
            player.sendMessage(ColorUtil.color("&c&l新命令追加! &7你现在可以使用/fw来发射烟花了"));
            AchievementManager.unlockAchievement(player,AchievementManager.getAchievement("TheSpringFestival2024"));
            player.setPlayerTime(18000L, false);
            Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_time","NIGHT");
            new BukkitRunnable() {
                @Override
                public void run() {
                    new FireWork().shootFirework();
                    counter++;
                    if (counter >= 72) {
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0L, 15L);
        }
    }

}
