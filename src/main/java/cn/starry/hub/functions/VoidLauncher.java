package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.parm.AutoRegister;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

@AutoRegister
public class VoidLauncher implements Listener {

    @EventHandler
    public void onPlayerVoid(PlayerMoveEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {

            final Location spawn = player.getWorld().getSpawnLocation();
            spawn.add(0.5, 0, 0.5);
            spawn.setYaw(Main.getPlugin(Main.class).getConfig().getInt("yaw"));
            spawn.setPitch(0);

            Location loc = player.getLocation();
            double playerY = player.getLocation().getY();

            if (Main.getInstance().getConfig().getBoolean("isVoidTp")) {
                if (playerY <= Main.getInstance().getConfig().getInt("Void-Y")) {
                    loc.getWorld().createExplosion(loc, 0.5F);
                    player.setVelocity(new Vector(0, 300, 0));
                }
            } else {
                if (playerY <= Main.getInstance().getConfig().getInt("Void-Y")) {
                    player.teleport(spawn);
                }
            }
        }
    }

}
