package cn.starry.hub.features.npc;

import cn.starry.core.utils.ClassUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.task.NPCRunnable;
import com.bnstra.npclib.NPCLib;
import com.bnstra.npclib.api.NPC;
import com.bnstra.npclib.api.events.NPCInteractEvent;
import com.bnstra.npclib.api.state.NPCSlot;
import com.bnstra.npclib.plugin.NPCLibPlugin;
import lombok.SneakyThrows;
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
public class NpcFactory implements Listener {

    private static final List<AbstractNPC> Npc = new ArrayList<>();

    public static List<AbstractNPC> getNpc() {
        return NpcFactory.Npc;
    }

    @SneakyThrows
    public void init() {
        NPCLib npcLib = new NPCLib(StarryLobby.getPlugin(StarryLobby.class));

        String type = StarryLobby.getInstance().getConfig().getString("type", "");
        String packageName = switch (type.toLowerCase()) {
            case "login" -> "cn.starry.hub.functions.npc.type.login";
            case "lobby" -> "cn.starry.hub.functions.npc.type.lobby";
            case "bedwars" -> "cn.starry.hub.functions.npc.type.bedwars";
            case "prototype" -> "cn.starry.hub.functions.npc.type.prototype";
            default -> null;
        };

        if (packageName == null) {
            return;
        }

        Collection<Class<?>> classes = ClassUtil.getClassesInPackage(StarryLobby.getInstance(), packageName);

        for (Class<?> clazz : classes) {
            System.out.println(clazz.getSimpleName());
            if (AbstractNPC.class.isAssignableFrom(clazz)) {
                AbstractNPC abstractNPC = (AbstractNPC) clazz.getDeclaredConstructor().newInstance();

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

        new NPCRunnable().runTaskTimerAsynchronously(StarryLobby.getInstance(), 0, 3L);

    }

    @EventHandler
    @SneakyThrows
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (AbstractNPC Npc : Npc) {
            Npc.getNpc().create();
            Npc.getNpc().setText(player, Npc.getNpcTextLine(player));
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

