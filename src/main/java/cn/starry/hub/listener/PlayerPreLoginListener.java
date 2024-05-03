package cn.starry.hub.listener;

import cn.starry.hub.utils.NickUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerPreLoginListener implements Listener {

    @EventHandler
    public void onPreLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (NickUtil.isNicked(player.getUniqueId())) {
            NickUtil.setNick(player, NickUtil.getNickName(player.getUniqueId()));
        }
    }
}
