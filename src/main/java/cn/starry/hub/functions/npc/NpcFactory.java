package cn.starry.hub.functions.npc;

import cn.starry.hub.Main;
import cn.starry.hub.functions.npc.runnable.NpcRunnable;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ClassUtil;
import lombok.SneakyThrows;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.state.NPCSlot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: EmptyIrony
 * @Date: 2020/12/30 22:35
 */
@AutoRegister
public class NpcFactory implements Listener {
    private static final List<AbstractNPC> Npc = new ArrayList<>();

    public static List<AbstractNPC> getNpc() {
        return NpcFactory.Npc;
    }

    @SneakyThrows
    public void init() {
        NPCLib npcLib = new NPCLib(Main.getInstance());

        Collection<Class<?>> classes = null;
        if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            classes = ClassUtil.getClassesInPackage(Main.getInstance(), "cn.starry.hub.functions.npc.type.lobby");
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            classes = ClassUtil.getClassesInPackage(Main.getInstance(), "cn.starry.hub.functions.npc.type.bedwars");
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
            classes = ClassUtil.getClassesInPackage(Main.getInstance(), "cn.starry.hub.functions.npc.type.megawalls");
        } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("Login")) {
            classes = ClassUtil.getClassesInPackage(Main.getInstance(), "cn.starry.hub.functions.npc.type.login");
        }
        for (Class<?> clazz : classes) {
            if (AbstractNPC.class.isAssignableFrom(clazz)) {
                AbstractNPC abstractNPC = (AbstractNPC) clazz.newInstance();

                NPC npc = npcLib.createNPC();
                npc.setLocation(abstractNPC.getNpcSpawnLocation());

                if (abstractNPC.getNpcHeldItem() != null) {
                    npc.setItem(NPCSlot.MAINHAND, abstractNPC.getNpcHeldItem());
                }

                if (abstractNPC.getNpcHelmetItem() != null) {
                    npc.setItem(NPCSlot.HELMET, abstractNPC.getNpcHelmetItem());
                }

                abstractNPC.setNpc(npc);

                Npc.add(abstractNPC);
            }
        }

        new NpcRunnable().runTaskTimerAsynchronously(Main.getInstance(), 0, 3L);
    }

    @EventHandler
    @SneakyThrows
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (AbstractNPC Npc : Npc) {
            Npc.getNpc().create();
            Npc.getNpc().setPlayerLines(Npc.getNpcTextLine(player), player, true);
            if (Npc.getNpcSkin(player) != null) {
                Npc.getNpc().setSkin(Npc.getNpcSkin(player));
            }
            if (!Npc.getNpc().isShown(player)) {
                Npc.getNpc().show(player);
            }
        }

    }

    @EventHandler
    public void onInteract(NPCInteractEvent event) {
        for (AbstractNPC abstractNPC : Npc) {
            if (abstractNPC.getNpc().getUniqueId().equals(event.getNPC().getUniqueId())) {
                abstractNPC.handlePlayerInteract(event.getWhoClicked());
            }
        }
    }

}
