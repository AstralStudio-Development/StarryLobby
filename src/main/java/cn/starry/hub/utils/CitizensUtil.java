package cn.starry.hub.utils;

import cn.starry.hub.StarryLobby;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.Gravity;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.LookClose;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;

public class CitizensUtil {
    public static void addDefaultSettings(NPC npc) {
        npc.getOrAddTrait(LookClose.class).lookClose(true);
        if (npc.getOrAddTrait(Gravity.class).hasGravity()) {
            npc.getOrAddTrait(Gravity.class).toggle();
        }
        npc.data().setPersistent("silent-sounds", true);
        npc.data().setPersistent("ambient-sound", "");
        npc.data().setPersistent("death-sound", "");
        npc.data().setPersistent("hurt-sound", "");
    }

    public static void addHolo(NPC npc, String ... s) {
        HologramTrait holo = npc.getOrAddTrait(HologramTrait.class);
        holo.setLineHeight(0.25);
        Arrays.stream(s).forEach(holo::addLine);
    }

    public static void addMetadata(NPC npc, String key, Object value) {
        npc.getEntity().setMetadata(key, new FixedMetadataValue(StarryLobby.getInstance(), value));
    }
}