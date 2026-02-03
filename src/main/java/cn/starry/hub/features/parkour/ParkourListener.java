package cn.starry.hub.features.parkour;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.features.parkour.ParkourManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ParkourListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ParkourManager.getInstance().loadBestTime(event.getPlayer());
        ParkourManager.getInstance().showHolograms(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        ParkourManager.getInstance().cancelParkour(event.getPlayer(), true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        // Hologram Interaction (Start/End)
        // ProtocolLib handles packets, so Bukkit events might not trigger on invisible armor stands unless we are careful.
        // Actually, PacketHologram uses packets, so no real entity exists on server side to click.
        // However, usually players click the air or block near it.
        // Or if we used real ArmorStands (HolographicDisplays), we would get EntityDamageByEntityEvent or PlayerInteractEntityEvent.
        // With PacketHologram, there are no entities to click. 
        // We must listen for PacketType.Play.Client.USE_ENTITY via ProtocolLib to detect interaction with the fake entities.
        // BUT, the user prompt says "Right or Left click Hologram". 
        // Since we switched to PacketHologram, we need to add a PacketListener in ParkourManager or here.
        // Let's defer this to ParkourManager to keep logic together or add ProtocolLib listener here?
        // Wait, I can't add ProtocolLib listener in this standard Bukkit Listener class easily without registering it.
        // I will add a method in ParkourManager to register the packet listener.
        
        // Pressure Plate Logic
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock() != null) {
            ParkourManager.getInstance().checkPhysicalInteract(player, event.getClickedBlock());
        }
        
        // Item Logic
        if (ParkourManager.getInstance().isPlaying(player)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                int slot = player.getInventory().getHeldItemSlot();
                switch (slot) {
                    case 4: // Iron Plate - Checkpoint
                        event.setCancelled(true);
                        ParkourManager.getInstance().checkpoint(player);
                        break;
                    case 5: // Oak Door - Reset
                        event.setCancelled(true);
                        // Reset via Item: Teleport = true, Message = true
                        ParkourManager.getInstance().resetParkour(player, true, true);
                        break;
                    case 6: // Red Bed - Cancel
                        event.setCancelled(true);
                        // Cancel via Item: Silent = false (Send Message)
                        ParkourManager.getInstance().cancelParkour(player, false);
                        break;
                }
            }
        }
    }
}

