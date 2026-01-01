package cn.starry.hub.utils.nametag;

import dev.jnic.annotations.Include;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Include
@Getter
public class CautusListeners implements Listener {

    private CautusHandler cautusHandler;

    /**
     * Nametag Listeners.
     *
     * @param cautusHandler instance.
     */
    public CautusListeners(CautusHandler cautusHandler) {
        this.cautusHandler = cautusHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.cautusHandler.getBoards().putIfAbsent(event.getPlayer().getUniqueId(), new CautusBoard(event.getPlayer(), this.cautusHandler));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CautusBoard board = this.cautusHandler.getBoards().get(event.getPlayer().getUniqueId());

        if (board == null) {
            return;
        }

        board.cleanup();
        this.cautusHandler.getBoards().remove(event.getPlayer().getUniqueId());
        event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

}
