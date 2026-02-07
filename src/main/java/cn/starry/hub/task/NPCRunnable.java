package cn.starry.hub.task;

import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.NpcFactory;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Starry_Killer
 */
public class NPCRunnable extends BukkitRunnable {

    @Override
    public void run() {
        /*
        //for (Player player : Bukkit.getOnlinePlayers()) {
            for (AbstractNPC npc : NpcFactory.getNpc()) {
                //npc.getNpc().setText(player, npc.getNpcTextLine(player));
                if (npc.getNpcSkin() != null) {
                    npc.getNpc().getOrAddTrait(SkinTrait.class).setSkinName(npc.getNpcSkin());
                }
            }
        //}

         */
    }

}


