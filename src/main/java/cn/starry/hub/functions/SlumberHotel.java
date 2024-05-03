package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class SlumberHotel implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (containsDoor(event.getTo())) {
            player.setVelocity(new Vector(-2.0,0.5,0));
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.5f, 1.5f);
            player.sendMessage(ColorUtil.color("&c前面的&3入梦酒店&c区域， 以后再来探索吧！"));
            Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
                player.setVelocity(new Vector(-2.0,0.5,0));
                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.5f, 1.5f);
            }, 5L);
        }
        Location to = event.getTo();
        if (to.clone().add(0, -1, 0).getBlock().getType() == Material.SLIME_BLOCK) {
            player.setVelocity(new Vector(2.0,1.0,0.0));
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.0f, 1.0f);
        }
    }

    private boolean containsDoor(Location location) {
        World world = Bukkit.getWorld("world");
        Location a = new Location(world,27,68,-2);
        Location b = new Location(world,29,76,3);

        double xPos1 = Math.min(a.getX(),b.getX());
        double yPos1 = Math.min(a.getY(),b.getY());
        double zPos1 = Math.min(a.getZ(),b.getZ());
        double xPos2 = Math.max(a.getX(),b.getX());
        double yPos2 = Math.max(a.getY(),b.getY());
        double zPos2 = Math.max(a.getZ(),b.getZ());

        Vector minV = new Vector(xPos1, yPos1, zPos1);
        Vector maxV = new Vector(xPos2, yPos2, zPos2);

        return location.toVector().isInAABB(minV, maxV);
    }
}
