package cn.starry.hub.task;

import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.NpcFactory;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * @author Starry_Killer
 */
public class NPCRunnable extends BukkitRunnable {

    @Override
    public void run() {
        // Citizens NPCs are global. We cannot easily support per-player text/skin without complex packet handling.
        // We will update the hologram based on the first player found, or skip if no players.
        Player samplePlayer = Bukkit.getOnlinePlayers().stream().findFirst().orElse(null);
        if (samplePlayer == null) return;

        for (AbstractNPC abstractNPC : NpcFactory.getNpc()) {
            if (abstractNPC.getNpc() == null || !abstractNPC.getNpc().isSpawned()) continue;

            // Update Hologram
            List<String> lines = abstractNPC.getNpcTextLine(samplePlayer);
            if (lines != null && !lines.isEmpty()) {
                HologramTrait hologram = abstractNPC.getNpc().getOrAddTrait(HologramTrait.class);
                // Check if lines need update to avoid unnecessary processing
                if (!lines.equals(hologram.getLines())) {
                    hologram.clear();
                    for (String line : lines) {
                        hologram.addLine(line);
                    }
                }
            }

            // Skin updates are skipped to prevent constant respawning/flickering.
            // Citizens skins are generally persistent and handled in NpcFactory init.
        }
    }

}
