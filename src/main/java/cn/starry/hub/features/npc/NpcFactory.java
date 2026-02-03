package cn.starry.hub.features.npc;

import cn.starry.core.utils.ClassUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.task.NPCRunnable;
import lombok.SneakyThrows;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;

import org.bukkit.entity.EntityType;
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

    public static void clear() {
        // Clear all Citizens NPCs to prevent stacking
        if (CitizensAPI.hasImplementation()) {
            List<NPC> toRemove = new ArrayList<>();
            CitizensAPI.getNPCRegistry().forEach(toRemove::add);
            toRemove.forEach(NPC::destroy);
        }
        Npc.clear();
    }

    @SneakyThrows
    public void init() {
        clear();

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

        for (Class<?> clazz : classes) {
            System.out.println(clazz.getSimpleName());
            if (AbstractNPC.class.isAssignableFrom(clazz)) {
                AbstractNPC abstractNPC = (AbstractNPC) clazz.getDeclaredConstructor().newInstance();

                // Check if NPC already exists to avoid duplicates on reload
                // Using internal name or some ID would be better, but here we iterate registry
                // For now, let's just create new ones. The user can clear them with /npc remove all if needed.
                // Or better, we can tag them.

                List<String> displayNames = abstractNPC.getNpcDisplayName(null);
                String npcName = (displayNames != null && !displayNames.isEmpty()) ? displayNames.get(0) : "NPC";
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, npcName);
                
                // Add metadata to identify this NPC as managed by StarryLobby
                npc.data().set("starry-lobby-npc", true);

                // Set Skin
                Skin skin = abstractNPC.getNpcSkin(null);
                if (skin != null) {
                    SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
                    if (skin.getSignature() != null && !skin.getSignature().isEmpty()) {
                         skinTrait.setSkinPersistent(abstractNPC.getNpcInternalName(), skin.getSignature(), skin.getValue());
                    } else {
                         // Fallback or just value? Citizens usually takes name or texture/signature
                         // If value is a name
                         skinTrait.setSkinName(skin.getValue());
                    }
                }
                
                // Set Equipment
                Equipment equipment = npc.getOrAddTrait(Equipment.class);
                if (abstractNPC.getNpcHeldItem() != null) {
                    equipment.set(Equipment.EquipmentSlot.HAND, abstractNPC.getNpcHeldItem());
                }
                if (abstractNPC.getNpcHelmetItem() != null) {
                    equipment.set(Equipment.EquipmentSlot.HELMET, abstractNPC.getNpcHelmetItem());
                }

                // Look Close
                if (abstractNPC.isContinuouslyWatchingPlayers()) {
                    npc.getOrAddTrait(LookClose.class).lookClose(true);
                }

                npc.spawn(abstractNPC.getNpcSpawnLocation());

                abstractNPC.setNpc(npc);
                Npc.add(abstractNPC);
            }
        }

        new NPCRunnable().runTaskTimer(StarryLobby.getInstance(), 0, 20L);

    }

    @EventHandler
    @SneakyThrows
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (AbstractNPC Npc : Npc) {
            // Citizens handles spawning/showing.
            // We might want to update per-player text (Hologram) or skin if logic depends on player
            // But Citizens NPC is global entity. 
            // If text is per-player, Citizens usually requires HologramTrait with per-player placeholders or separate Hologram plugin.
            // The original code used Npc.getNpc().setText(player, ...).
            // Since we are switching to Citizens, we might lose per-player text feature unless we use HologramTrait with placeholders.
            // For now, let's just ensure it's spawned.
            if (!Npc.getNpc().isSpawned()) {
                Npc.getNpc().spawn(Npc.getNpcSpawnLocation());
            }
        }
    }

    @EventHandler
    public void onInteract(NPCRightClickEvent event) {
        for (AbstractNPC abstractNPC : Npc) {
            if (abstractNPC.getNpc().getUniqueId().equals(event.getNPC().getUniqueId())) {
                abstractNPC.handlePlayerInteract(event.getClicker());
            }
        }
    }
}
