package cn.starry.hub.features.npc.type.login;

import cn.starry.core.Core;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.menus.login.AgreementMenu;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.Skin;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/4/23
 */
@Include
public class AgreementNPC extends AbstractNPC {

    private HashMap<Player,Integer> round = new HashMap<>();

    @Override
    public String getNpcInternalName() {
        return "agreement";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&a玩家协议签署");
        lines.add("&f右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"), 14.5, 90.0, 7.5, 115, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcyMzM5OTIwMDE3MiwKICAicHJvZmlsZUlkIiA6ICI3ZDcxMzY3YzMzNTI0NWY4OWUwNDA4YzdjZWZjNWIwOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJLZWxpcG90XyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MTc2OWNiMTc5YzQxNTgwOTEwOTAzMjQ4ZGM0ODgyOTEwNGUyNTBhMDliZDg5MmNkMTMwOTNmYjgzNDk0NDk1IgogICAgfQogIH0KfQ==";
        String signature = "nPXT95qlVL1xWNn5hO7yKWSM+AEbsNi2NZS1/780P88BK+Wixnj1exUWbyMe7YpyZkaPaYvgmxI93hrPmJlfsvYCxP9HMvQTV4mEUY139hjgj5hIhqy/0FIhHMI0a33dvxZUUU5RKD8hRSseHNTi+FlpN8ZD/kFL/ZP1kiDNj13+oE+INc0+ahpPH7WtE79M9fDf9q1Yc8fPLb4oxoAh9PkDuUA2GYoyloG+dQU0uj15T9TVu7S1AzNMrw1YN6vrnmsS34g1vnk+j8op0R6Q8Ctf5XFmuVJB2WRzIJ90vmKhq/HMzNRJXjKPUJU32/zqp6xhgT8nMDHLJ5Py79uVLAS8Lq4/b0LSS3MnumbUSlfizYys7Ihlv4CjbLE3WKX8IX5W2B4Ar370gSHGc4oMDd9lm8pNPHRKh3UlCNsaBuG03qF7rNnePab1KSNRxKEDTFi+0utVpgtjTK4yO+rZdwe/hzZbHCYMe4yFnd/w+Cpsu/IqWukYrMqa4p8/i9ILb1wPfp9LqWYgLP6jqf71X5IeLuXR0eyf6kYBP/A6YxQGtBqrrvqdQ1qgVPYQbkldgZa19dVI4lRhK4TNES4j25FxeKBZlclFpqH4ijHnpZomab9KGAoPMsPIiR4Utg9X2vmG3wVCEsC+7iYekjq4Hng0zAl8xkcYWp9KCLGr5g8=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        boolean a = Boolean.parseBoolean(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"isLoginBefore"));
        if (!a) {
            if (!round.containsKey(player)) {
                round.put(player, -1);
            }
            new BukkitRunnable() {
                public void run() {
                    if (round.containsKey(player) && !round.get(player).equals(3)) {
                        round.put(player, round.get(player) + 1);
                        sendMessage(player);
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(StarryLobby.getInstance(), 0L, 100L);
        } else {
            new AgreementMenu().openMenu(player);
        }
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return null;
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
            player.sendMessage(CC.translate("&e[NPC] Steve&f: 看样子,你应该是第一次来到这里. &7[1/&a3&7]"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        } else if (round.get(player).equals(1)) {
            player.sendMessage(CC.translate("&e[NPC] Steve&f: 跟我来,让我们前往 &3主大厅. &7[2/&a3&7]"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        } else if (round.get(player).equals(2)) {
            player.sendMessage(CC.translate("&e[NPC] Steve&f: 仔细阅读协议,然后点击 &a'我同意上述协议'&f 来进入 &3主大厅. &7[&a3/&a3&7]"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            round.remove(player);
            Core.getInstance().getMongoDB().updatePlayerData(player.getUniqueId(),"isLoginBefore","true");
        }
    }

}


