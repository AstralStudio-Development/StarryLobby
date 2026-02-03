package cn.starry.hub.features.npc.type.lobby.fun;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/24
 */
public class FisherNPC extends AbstractNPC {

    private HashMap<Player,Integer> round = new HashMap<>();

    @Override
    public String getNpcInternalName() {
        return "fisher";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        // UUID uuid = player.getUniqueId();
        lines.add("&b渔夫");
        lines.add("&e&l右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),65.5,26.0,-22.5,100,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYyMzQwMDMzNjY4MSwKICAicHJvZmlsZUlkIiA6ICJhMjk1ODZmYmU1ZDk0Nzk2OWZjOGQ4ZGE0NzlhNDNlZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJWaWVydGVsdG9hc3RpaWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJlZTEyNGZiMzI2M2RjOTMzOTljZTc1NTdkNGIyNmRhZDczYzM2ZWRlYWNjNDk5MGFmYzVjN2Y2YzU5NDA2OCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
        String signature = "yfAJLnjhApFc7KVJfIRyfZzYV4fHdku5jRiov2REwexu7qnhbrk86HlaFu6UY7Lx9u3HES1UZ8GW/N0D5T+g4B7wrBK7kSjlhRcjZcFN9NBM/ajOBsYkdz3yvQJ3b0Hbu4XgXRC6TyZafAVeLEbAZ7Lt/UCbNM/o1euLo1viKLqFpponlyBkaWAYnIgiSRz/+8jWQBZbPjd0RBXALUcxIPUf2HcgRbVjAIgGLAPFw9QH9gGoCgoViABPIHU97rHwTPRaCm7oRnMjOPusC4AN9z2vLi4ZUFOBJSDaxHaglcilNmwHLPhkQMMPru2jI1hKXg+rtGEdTyp2gJS5U09Qo4X3hcokJhAdfIX/QyygCL00JAB7xZjl6ex+6XQ3EyOwTtoNV2oyLJZVrSG1XUMU0xjuB4mA0npoGHWgb6niUEtjVP+g61DQQf3a6KPYaPfEdbJNRubvMC4NRWJ2vAN+GUYZxf2KBifVZr5+5kwpkov+e5I3ujIJVw4ARV0E/gOOpyeLM5X79Q2SX33fzlNWNYs2x/PRwD5PxK+eHVsdGea2+2Dnb0wIefN+D/KHMFZVEqZ7qtkhxXncUES2DojcoTQcrAsy219c1nCi4jw01pM1QfvHH4S59cvDRq25OCNG0u4fFmJIfxDrvSL3Oz/CsoNyScQK8RJE31ipnhB6BP8=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        if (!round.containsKey(player)) {
            round.put(player, -1);
        }
        new BukkitRunnable(){
            public void run() {
                if (round.containsKey(player) && !round.get(player).equals(2)) {
                    round.put(player, round.get(player) + 1);
                    sendMessage(player);
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(StarryLobby.getInstance(), 0L,100L);
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.FISHING_ROD).build();
    }

    @Override
    public ItemStack getNpcHelmetItem() {
        return null;
    }

    @Override
    public boolean isContinuouslyWatchingPlayers() {
        return false;
    }

    private void sendMessage(Player player) {
        if (round.get(player).equals(0)) {
            player.sendMessage(CC.translate("&e[NPC] 渔夫&f: 你好,我在这里钓了一个下午了. &7[1/&a3&7]"));
        } else if (round.get(player).equals(1)) {
            player.sendMessage(CC.translate("&e[NPC] 渔夫&f: 但是没有一只鱼上钩,太奇怪了. &7[2/&a3&7]"));
        } else if (round.get(player).equals(2)) {
            player.sendMessage(CC.translate("&e[NPC] 渔夫&f: 你知道是什么情况吗? &7[&a3/&a3&7]"));
            round.remove(player);
        }
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
    }
}


