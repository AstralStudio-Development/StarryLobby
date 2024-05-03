package cn.starry.hub.utils;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarUtil extends BukkitRunnable {

    private String title;
    private static HashMap<Player, EntityWither> withers = new HashMap();

    public BossBarUtil(Plugin plugin, String title) {
        this.title = title;
        this.runTaskTimer(plugin, 0L, 20L);
    }

    public void addPlayer(Player player) {
        EntityWither wither = new EntityWither(((CraftWorld)(player.getWorld())).getHandle());
        Location location = player.getPlayer().getLocation();
        wither.setCustomName(this.title);
        wither.setInvisible(true);
        wither.setLocation(location.getX(), location.getY(), location.getZ(), 0.0f, 0.0f);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(wither);
        ((CraftPlayer)(player)).getHandle().playerConnection.sendPacket(packet);
        withers.put(player, wither);
    }

    public void removePlayer(Player player) {
        if (!withers.containsKey(player)) {
            return;
        }
        EntityWither wither = withers.remove(player);
        if (!(player instanceof CraftPlayer)) {
            return;
        }
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(wither.getId());
        ((CraftPlayer)(player)).getHandle().playerConnection.sendPacket(packet);
    }

    public void setTitle(String string) {
        this.title = string;
        for (Map.Entry<Player, EntityWither> entry : withers.entrySet()) {
            EntityWither wither = entry.getValue();
            wither.setCustomName(string);
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), true);
            ((CraftPlayer)(entry.getKey())).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public boolean containsPlayer(Player player) {
        return withers.containsKey(player);
    }

    public void run() {
        for (Map.Entry<Player, EntityWither> en : withers.entrySet()) {
            EntityWither wither = en.getValue();
            Location l = this.getWitherLocation(en.getKey().getLocation());
            wither.setLocation(l.getX(), l.getY(), l.getZ(), 0.0f, 0.0f);
            PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(wither);
            ((CraftPlayer)(en.getKey())).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public Location getWitherLocation(Location l) {
        return l.add(l.getDirection().multiply(20));
    }

    public void setProgress(double progress) {
        for (Map.Entry<Player, EntityWither> entry : withers.entrySet()) {
            EntityWither wither = entry.getValue();
            wither.setHealth((float)(progress * (double)wither.getMaxHealth()));
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), true);
            ((CraftPlayer)(entry.getKey())).getHandle().playerConnection.sendPacket(packet);
        }
    }
}