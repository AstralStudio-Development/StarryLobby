package cn.starry.hub.utils.nametag;

import cn.starry.core.Core;
import cn.starry.core.utils.nametag.NametagBoard;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@Include
public class CautusThread extends Thread {

    private CautusHandler handler;
    protected int taskId;

    /**
     * Cautus Thread.
     *
     * @param handler instance.
     */
    public CautusThread(CautusHandler handler) {
        this.handler = handler;
        Plugin protocolLib = Bukkit.getPluginManager().getPlugin("ProtocolLib");
        this.taskId = protocolLib.getDescription().getVersion().startsWith("5") ? Bukkit.getScheduler().runTaskTimer(Core.getInstance(), this, handler.getTicks(), handler.getTicks()).getTaskId() : Bukkit.getScheduler().runTaskTimerAsynchronously(Core.getInstance(), this, handler.getTicks(), handler.getTicks()).getTaskId();
    }

    @Override
    public void run() {
        if (!Core.getInstance().isEnabled()) {
            return;
        }
        this.tick();
    }

    /**
     * Thread Tick Logic.
     */
    private void tick() {
        if (this.handler.getAdapter() == null) {
            return;
        }

        for (Player player : this.handler.getPlugin().getServer().getOnlinePlayers()) {
            CautusBoard board = this.handler.getBoards().get(player.getUniqueId());

            // This shouldn't happen, but just in case.
            if (board != null) {
                board.update();
            }
        }
    }

}
