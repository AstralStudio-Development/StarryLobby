package cn.starry.hub.functions.npc.runnable;

import cn.starry.hub.functions.npc.AbstractNPC;
import cn.starry.hub.functions.npc.NpcFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author EmptyIrony, Starry_Killer
 * @date 2021/1/1 21:09
 */
public class NpcRunnable extends BukkitRunnable {

    public static int c = 0;

    @Override
    public void run() {
        if (c >= 11) {
            c = 0;
        } else {
            c++;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (AbstractNPC Npc : NpcFactory.getNpc()) {
                Npc.getNpc().setPlayerLines(Npc.getNpcTextLine(player), player, true);
                if (Npc.getNpcSkin(player) != null) {
                    Npc.getNpc().setSkin(Npc.getNpcSkin(player));
                }
            }
        }
    }

}
