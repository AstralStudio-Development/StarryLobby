package cn.starry.hub.utils.toolkit.citizens;

import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.Gravity;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;

public class CitizensUtil {
    public static void addDefaultSettings(NPC npc, boolean lookClose) {
        npc.getOrAddTrait(LookClose.class).lookClose(lookClose);
        if (npc.getOrAddTrait(Gravity.class).hasGravity()) {
            npc.getOrAddTrait(Gravity.class).toggle();
        }

        npc.data().setPersistent("silent-sounds", true);
        npc.data().setPersistent("ambient-sound", "");
        npc.data().setPersistent("death-sound", "");
        npc.data().setPersistent("hurt-sound", "");

        npc.scheduleUpdate(NPC.NPCUpdate.PACKET);

        npc.data().setPersistent(NPC.Metadata.COLLIDABLE, false);
        npc.data().setPersistent(NPC.Metadata.NAMEPLATE_VISIBLE, false);
    }

    public static void addHolo(NPC npc, String ... s) {
        HologramTrait holo = npc.getOrAddTrait(HologramTrait.class);
        holo.setLineHeight(0.25);
        Arrays.stream(s).forEach(holo::addLine);
    }

    public static void addMetadata(NPC npc, String key, Object value) {
        npc.getEntity().setMetadata(key, new FixedMetadataValue(StarryLobby.getInstance(), value));
    }

    public static void setSkin(NPC npc, AbstractNPC abstractNPC) {
        if (npc.getEntity() != null) {
            if (npc.getEntity().getType().equals(EntityType.PLAYER)) {
                if (!npc.hasTrait(SkinTrait.class)) {
                    npc.addTrait(SkinTrait.class);
                }
                SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
                Skin npcSkin = abstractNPC.getNpcSkin(null);

                if (npcSkin == null) {
                    return;
                }
                skinTrait.setSkinPersistent("custom_skin_name", npcSkin.getSignature(), npcSkin.getValue());
            }
        }
    }
}