package cn.starry.hub.functions.settings;


import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;


public class PlayerShowEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_show").equalsIgnoreCase("TRUE")) {
                for (Player p : getServer().getOnlinePlayers()) {
                        player.showPlayer(p);
                }
            } else {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    player.hidePlayer(p);
                }
            }
        }
    }
}
