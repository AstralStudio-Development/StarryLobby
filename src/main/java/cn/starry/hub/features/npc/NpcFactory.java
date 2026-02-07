package cn.starry.hub.features.npc;

import cn.starry.core.utils.ClassUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.task.NPCRunnable;
import cn.starry.hub.utils.toolkit.citizens.CitizensUtil;
import lombok.SneakyThrows;
import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.CitizensPlugin;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.npc.CitizensNPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

/**
 * @Author: Stalyer
 * @Date: 2026/2/8
 */
public class NpcFactory implements Listener {

    private static final HashMap<String, AbstractNPC> Npc = new HashMap<>();
    private static final HashMap<UUID, AbstractNPC> NpcByUUID = new HashMap<>();

    //26.1 Support
    //private static CitizensPlugin plugin = (CitizensPlugin) Bukkit.getPluginManager().getPlugin("Citizens");

    private static NPCRegistry registry = new CitizensNPCRegistry(new MemoryNPCDataStore());;

    public static Collection<AbstractNPC> getNpc() {
        return NpcFactory.Npc.values();
    }

    public static HashMap<String, AbstractNPC> getNpcMap() {
        return NpcFactory.Npc;
    }

    @SneakyThrows
    public void init() {
        String type = StarryLobby.getInstance().getConfig().getString("type", "");
        String packageName = switch (type.toLowerCase()) {
            case "login" -> "cn.starry.hub.features.npc.type.login";
            case "lobby" -> "cn.starry.hub.features.npc.type.lobby";
            case "bedwars" -> "cn.starry.hub.features.npc.type.bedwars";
            case "prototype" -> "cn.starry.hub.features.npc.type.prototype";
            default -> null;
        };

        if (packageName == null) {
            return;
        }

        Collection<Class<?>> classes = ClassUtil.getClassesInPackage(StarryLobby.getInstance(), packageName);

        System.out.println("------------NPC LIST------------");
        for (Class<?> clazz : classes) {
            System.out.println(clazz.getSimpleName());
            if (AbstractNPC.class.isAssignableFrom(clazz)) {
                AbstractNPC abstractNPC = (AbstractNPC) clazz.getDeclaredConstructor().newInstance();

                NPC npc = registry.createNPC(EntityType.PLAYER, "");

                CitizensUtil.addDefaultSettings(npc, abstractNPC.isContinuouslyWatchingPlayers());

                if (abstractNPC.getNpcHeldItem() != null) {
                    npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, abstractNPC.getNpcHeldItem());
                }

                if (abstractNPC.getNpcHelmetItem() != null) {
                    npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, abstractNPC.getNpcHelmetItem());
                }

                npc.spawn(abstractNPC.getNpcSpawnLocation());

                abstractNPC.setNpc(npc);
                CitizensUtil.setSkin(npc, abstractNPC);

                Npc.put(abstractNPC.getNpcInternalName(), abstractNPC);
                NpcByUUID.put(abstractNPC.getNpc().getUniqueId(), abstractNPC);
            }
        }
        System.out.println("------------NPC LIST------------");

        //new NPCRunnable().runTaskTimerAsynchronously(StarryLobby.getInstance(), 20, 20L);

    }

    @EventHandler
    @SneakyThrows
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }

        for (AbstractNPC abstractNPC : Npc.values()) {
            if (abstractNPC.getNpcTextLine(player) != null) {
                List<String> lines = abstractNPC.getNpcTextLine(player);
                Collections.reverse(lines);
                for (String s: lines) {
                    CitizensUtil.addHolo(abstractNPC.getNpc(), s);
                }
            }

        }

    }

    @EventHandler
    public void onInteract(NPCLeftClickEvent event) {
        AbstractNPC abstractNPC = NpcByUUID.get(event.getNPC().getUniqueId());
        if (abstractNPC == null) {
            return;
        }
        abstractNPC.handlePlayerInteract(event.getClicker());
    }

    @EventHandler
    public void onInteract(NPCRightClickEvent event) {
        AbstractNPC abstractNPC = NpcByUUID.get(event.getNPC().getUniqueId());
        if (abstractNPC == null) {
            return;
        }
        abstractNPC.handlePlayerInteract(event.getClicker());
    }

}

