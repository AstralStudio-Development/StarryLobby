package cn.starry.hub.functions.settings;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.UUID;

@AutoRegister
public class PlayerTimeEvent implements Listener {

    @EventHandler
    public void TimeChange(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time") == null) {
            return;
        }
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("DAY")) {
                player.setPlayerTime(1200L, false);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("SUNSET")) {
                player.setPlayerTime(12650L, false);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("NIGHT")) {
                player.setPlayerTime(18000L, false);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:TIME_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
    }
}
