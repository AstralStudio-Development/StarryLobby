package cn.starry.hub.features.npc;

import cn.starry.core.utils.ClassUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.task.NPCRunnable;
import cn.starry.hub.utils.CitizensUtil;
import lombok.SneakyThrows;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.npc.SimpleNPCDataStore;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.util.YamlStorage;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: EmptyIrony
 * @Date: 2020/12/30 22:35
 */
public class NpcFactory implements Listener {

    private static final List<AbstractNPC> Npc = new ArrayList<>();
    private File store;

    public static List<AbstractNPC> getNpc() {
        return NpcFactory.Npc;
    }

    @SneakyThrows
    public void init() {
        this.store = new File(StarryLobby.getInstance().getDataFolder(), "npc.yml");
        NPCRegistry npcRegistry = CitizensAPI.createNamedNPCRegistry("StarryLobby", new SimpleNPCDataStore(new YamlStorage(this.store)));

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

                NPC npc = npcRegistry.createNPC(EntityType.PLAYER, abstractNPC.getNpcDisplayName(player));

                CitizensUtil.addDefaultSettings(npc);

                npc.getOrAddTrait(SkinTrait.class).setSkinName(abstractNPC.getNpcSkin());

                if (abstractNPC.getNpcHeldItem() != null) {
                    npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, abstractNPC.getNpcHeldItem());
                }

                if (abstractNPC.getNpcHelmetItem() != null) {
                    npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, abstractNPC.getNpcHelmetItem());
                }

                npc.spawn(abstractNPC.getNpcSpawnLocation());

                //abstractNPC.setNpc(npc);

                Npc.add(abstractNPC);
            }
        }

        //new NPCRunnable().runTaskTimerAsynchronously(StarryLobby.getInstance(), 0, 3L);

    }

    //@EventHandler
    @SneakyThrows
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        /*for (AbstractNPC Npc : Npc) {
            Npc.getNpc().create();
            Npc.getNpc().setText(player, Npc.getNpcTextLine(player));
            if (Npc.getNpcSkin(player) != null) {
                Npc.getNpc().setSkin(Npc.getNpcSkin(player));
            }
            if (!Npc.getNpc().isShown(player)) {
                Npc.getNpc().show(player);
            }
        }*/

    }

    @EventHandler
    public void onInteract(NPCClickEvent event) {
        for (AbstractNPC abstractNPC : Npc) {
            if (abstractNPC.getNpc().getUniqueId().equals(event.getNPC().getUniqueId())) {
                abstractNPC.handlePlayerInteract(event.getClicker());
            }
        }
    }

}

