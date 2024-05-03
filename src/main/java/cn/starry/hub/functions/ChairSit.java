package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.functions.achievement.AchievementManager;
import cn.starry.hub.parm.AutoRegister;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

@AutoRegister
public class ChairSit implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.SMOOTH_STAIRS) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getWorld().getBlockAt(event.getClickedBlock().getLocation().add(0,-1,0)).getType() == Material.LOG) {
                Player player = event.getPlayer();
                Location location = event.getClickedBlock().getLocation().clone().add(0.5, -0.5, 0.5);
                Arrow s = (Arrow) player.getWorld().spawnEntity(location, EntityType.ARROW);
                s.setPassenger(player);
                AchievementManager.unlockAchievement(player, AchievementManager.getAchievement("ChairCanSit"));
            }
        }
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (event.getDismounted() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getDismounted();
            if (!armorStand.isEmpty()) {
                armorStand.remove();
            }
        }
    }

}
