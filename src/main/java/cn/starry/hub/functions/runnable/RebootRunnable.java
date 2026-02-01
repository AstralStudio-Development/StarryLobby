package cn.starry.hub.functions.runnable;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.time.TimeUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.utils.TitleUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @Author: EmptyIrony
 * @Date: 2021/2/24 13:04
 */
@Getter
public class RebootRunnable extends BukkitRunnable {

    private final long serverStartTime;
    private RebootTask currentTask;

    public RebootRunnable() {
        this.serverStartTime = System.currentTimeMillis();
    }

    @Override
    public void run() {

        if (currentTask != null) {
            if (currentTask.endTime <= System.currentTimeMillis()) {
                Bukkit.getScheduler().runTask(StarryLobby.getInstance(), Bukkit::shutdown);
                return;
            }
            if (currentTask.endTime <= System.currentTimeMillis() + 5 * 1000) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(CC.translate("&4&l注意! &7此大厅即将重启."));
                }
            }
        }
    }

    public void addRebootTask(RebootTask task) {
        if (this.currentTask != null) {
            return;
        }
        this.currentTask = task;
        long l = task.endTime - System.currentTimeMillis();
        String time = TimeUtil.millisToRoundedTime(l);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(CC.translate("&4&l注意! &7一个计划中的重启即将在 &c" + time + "&7 后执行&8[#" + task.reason + "]"));
            TitleUtil.sendTitle(player, CC.translate("&c服务器即将在 &b" + time + " &c后重启"), "", 20, 100, 20);
        }
    }


    @Getter
    @RequiredArgsConstructor
    public static class RebootTask {
        private final String reason;
        private final long endTime;
    }
}
