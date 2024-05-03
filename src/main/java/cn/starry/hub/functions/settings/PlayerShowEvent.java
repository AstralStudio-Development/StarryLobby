package cn.starry.hub.functions.settings;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

@AutoRegister
public class PlayerShowEvent implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_show") == null) {
                return;
            }
            for (Player p : getServer().getOnlinePlayers()) {
                if (Main.getInstance().getData().getPlayerData(p.getUniqueId(),"vanish").equalsIgnoreCase("true")) {
                    player.hidePlayer(p);
                }
            }
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_show").equals("ENABLE")) {
                for (Player p : getServer().getOnlinePlayers()) {
                    if (!Main.getInstance().getData().getPlayerData(p.getUniqueId(),"vanish").equalsIgnoreCase("true") && !p.hasPermission("lobby.vanish")) {
                        player.showPlayer(p);
                    }
                }
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_show").equals("DISABLE")) {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (!Main.getInstance().getData().getPlayerData(p.getUniqueId(),"vanish").equalsIgnoreCase("true") && !p.hasPermission("lobby.vanish")) {
                        player.hidePlayer(p);
                    }
                }
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_show").equals("RANK")) {
                for (Player p : getServer().getOnlinePlayers()) {
                    if (p.hasPermission("lobby.rankshow")) {
                        if (!Main.getInstance().getData().getPlayerData(p.getUniqueId(),"vanish").equalsIgnoreCase("true") && !p.hasPermission("lobby.vanish")) {
                            player.showPlayer(p);
                        }
                    } else {
                        player.hidePlayer(p);
                    }
                }
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:SHOW_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
    }
}
