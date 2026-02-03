package cn.starry.hub.task;

import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.NpcFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Starry_Killer
 */
public class NPCRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (AbstractNPC Npc : NpcFactory.getNpc()) {
                Npc.getNpc().setText(player, Npc.getNpcTextLine(player));
                if (Npc.getNpcSkin(player) != null) {
                    Npc.getNpc().setSkin(Npc.getNpcSkin(player));
                }
            }
        }
    }

}


