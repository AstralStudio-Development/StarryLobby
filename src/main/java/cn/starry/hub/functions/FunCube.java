package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

/**
 * @Author: Starry_Killer, duduskz
 * @Date: 2023/12/28
 */
public class FunCube implements Listener {

    private Set<Entity> armorStands = new HashSet<>();
    private ArmorStand armorStand;
    private Location cubeLocation = new Location(getServer().getWorld("world"), -48.0,94.1,-3.0);

    public void createArmorStand() {
        Location location = cubeLocation;
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setHelmet(new ItemBuilder(Material.BOOKSHELF).shiny().build());
        armorStand.setCanPickupItems(false);
        armorStand.setRemoveWhenFarAway(false);
        armorStand.setArms(false);
        armorStand.setBasePlate(false);
        armorStand.setMetadata("cube_tutorial",new FixedMetadataValue(Main.getInstance(), true));
        armorStands.add(armorStand);
        createHologram(location.add(0.0, 2.2, 0.0), Arrays.asList("§8YumeGames 新手教程","§e§l右键点击"));
        new BukkitRunnable() {
            double angle = 0;
            double height = 0;
            boolean movingUp = true;

            @Override
            public void run() {

                if (movingUp) {
                    height += 0.005;
                    if (height >= 0.05) {
                        movingUp = false;
                    }
                } else {
                    height -= 0.005;
                    if (height <= -0.05) {
                        movingUp = true;
                    }
                }

                Location currentLocation = new Location(armorStand.getWorld(),armorStand.getLocation().getX(),armorStand.getLocation().getY(),armorStand.getLocation().getZ(),armorStand.getLocation().getYaw() + 3,armorStand.getLocation().getPitch());
                Location newLocation = currentLocation.clone().add(0, height, 0);
                armorStand.teleport(newLocation);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, 0);
    }

    public void removeArmorStand() {
        for (Entity entity : armorStands) {
            entity.remove();
        }
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().hasMetadata("cube_tutorial")) {
            new Tutorial().onTutorial(event.getPlayer());
        }
    }

    //Holograms
    public void createHologram(Location location, List<String> list) {
        Location location2 = location.clone().add(0.0, 0.35 * (double)list.size() - 1.97, 0.0);
        for (int i = 0; i < list.size(); ++i) {
            this.setLines(location2, list.get(i));
            location2.add(0.0, -0.35, 0.0);
        }
    }

    private void setLines(Location location, String string) {
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', string));
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setCanPickupItems(false);
    }

}
