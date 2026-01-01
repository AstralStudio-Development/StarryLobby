package cn.starry.hub.functions.activity.springfestival.Y2024;

import cn.starry.core.Core;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.utils.TitleUtil;
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
            player.sendMessage(CC.translate("&c&l新命令追加! &7你现在可以使用/fw来发射烟花了"));
            AchievementManager.unlockAchievement(player, AchievementManager.getAchievement("TheSpringFestival2024"));
            player.setPlayerTime(18000L, false);
            Core.getInstance().getMongoDB().updatePlayerData(uuid,"settings_lobby_time","NIGHT");
            new BukkitRunnable() {
                @Override
                public void run() {
                    new FireWork().shootFirework();
                    counter++;
                    if (counter >= 72) {
                        cancel();
                    }
                }
            }.runTaskTimer(StarryLobby.getInstance(), 0L, 15L);
        }
    }

}
