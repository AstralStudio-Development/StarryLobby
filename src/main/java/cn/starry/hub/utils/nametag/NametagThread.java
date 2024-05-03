package cn.starry.hub.utils.nametag;

import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NametagThread extends Thread {

    private NametagHandler handler;
    protected int taskId;

    /**
     * Nametag Thread.
     *
     * @param handler instance.
     */
    public NametagThread(NametagHandler handler) {
        this.handler = handler;
        Plugin protocolLib = Bukkit.getPluginManager().getPlugin("ProtocolLib");
        this.taskId = protocolLib.getDescription().getVersion().startsWith("5") ? Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this, handler.getTicks(), handler.getTicks()).getTaskId() : Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, handler.getTicks(), handler.getTicks()).getTaskId();
    }

    @Override
    public void run() {
        if (!Main.getInstance().isEnabled()) {
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
            NametagBoard board = this.handler.getBoards().get(player.getUniqueId());

            // This shouldn't happen, but just in case.
            if (board != null) {
                board.update();
            }
        }
    }
}
