package cn.starry.hub.utils.nametag;

import dev.jnic.annotations.Include;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Include
@Getter @Setter
public class CautusHandler {

    private JavaPlugin plugin;

    private CautusAdapter adapter;
    private CautusThread thread;
    private CautusListeners listeners;

    private Map<UUID, CautusBoard> boards;
    private long ticks = 2;
    private boolean hook = false;

    /**
     * Cautus Handler.
     *
     * @param plugin instance.
     * @param adapter to display nametags.
     */
    public CautusHandler(JavaPlugin plugin, CautusAdapter adapter) {
        if (plugin == null) {
            throw new RuntimeException("Ostentus can not be instantiated without a plugin instance!");
        }

        this.plugin = plugin;
        this.adapter = adapter;
        this.boards = new ConcurrentHashMap<>();

        this.setup();
    }

    /**
     * Setup Cautus.
     */
    public void setup() {
        // Register Events.
        this.listeners = new CautusListeners(this);
        this.plugin.getServer().getPluginManager().registerEvents(this.listeners, this.plugin);

        for (Player player : Bukkit.getOnlinePlayers()) {
            this.boards.putIfAbsent(player.getUniqueId(), new CautusBoard(player, this));
        }

        this.thread = new CautusThread(this);
    }

    /**
     * Cleanup Cautus.
     */
    public void cleanup() {
        // Unregister Thread.
        if (this.thread != null) {
            this.thread.cancel();
            this.thread = null;
        }

        // Unregister Listeners.
        if (this.listeners != null) {
            HandlerList.unregisterAll(this.listeners);
            this.listeners = null;
        }

        // Destroy boards.
        for (UUID uuid : getBoards().keySet()) {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null || !player.isOnline()) {
                continue;
            }

            this.boards.remove(uuid);
            if (!this.hook) {
                player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
        }
    }

}
