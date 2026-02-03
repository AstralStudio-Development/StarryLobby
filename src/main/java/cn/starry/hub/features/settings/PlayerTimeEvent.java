package cn.starry.hub.features.settings;


import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.UUID;


public class PlayerTimeEvent implements Listener {

    @EventHandler
    public void TimeChange(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("DAY")) {
                player.setPlayerTime(1200L, false);
            } else if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("SUNSET")) {
                player.setPlayerTime(12650L, false);
            } else if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("NIGHT")) {
                player.setPlayerTime(18000L, false);
            } else {
                player.sendMessage(CC.translate("&c发生了一个错误，类型:TIME_TYPE"));
                player.sendMessage(CC.translate("&c请将错误截图反馈至管理员"));
            }
        }
    }
}

