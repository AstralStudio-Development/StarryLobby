package cn.starry.hub.functions.npc.type.lobby;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.npc.AbstractNPC;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/24
 */
public class LevelGuideNPC extends AbstractNPC {

    private HashMap<Player,Integer> round = new HashMap<>();

    @Override
    public String getNpcInternalName() {
        return "level_guide";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        if (languageType.equals(LanguageType.CHINESE)) {
            lines.add("&3YumeGames等级向导");
            lines.add("&e&l右键点击");
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-45.5,87.0,55.5,-180,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwNjA5Njg2MDAwMSwKICAicHJvZmlsZUlkIiA6ICJiYzU0ZmNjYWQ5NWI0ZDdhOWEzN2MwNzljMjlkMWY3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJodmh0cmVuZHNldHRlciIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZmUwZjFkMjYwMGNhZDA4ODg0ODI3YmIzODBhMDQ0NDZlZDljOGY0YWFlMjQ0ODQ0MTc3NWQ1ODJkMmZjNDViIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
        String signature = "ZnRbMDlIcAk4d9emU/MPbETeNeS3Ydd2Tmr739gN3UVgAQdbNl7cBRLYc1E4NXpAIgWbs7aMB/geOS8P3vDBsz+5mbW+Akr+LYuRovz7VfRmbpdU/Lzg1FCZyvV47xYdl79o9mzsaTBB9gv/ok5+i8yqYMs5CfW3591fohNAdmn8JYMEU4GS7+7GDPecEyEy0420siS8mTkwDjnklCivuq1GZGzNUy8R5rO+G//tFOWn+LrU3xzPIj6nn4I/j2eBce91t4Qzawg9YTEsLdIhdp19ItLuQaIqzVXf5EGYA2DQ6yScebL3Jqu3oKOc0CPP7HbgGdz9ljgtNA5SKOCaEqIl9+edcgXq3oQ07rOatgepFkPFxC4Rm9WgHnd6kBtMUxWss/qbvIfOKknKfwFTATz+HILi1K1i/fmWkMzu5kT2f+X0RTNAV+Ws9UktYbGsUNLo8CZX+tfGL+1AM4h43PcX/mhAgyqJWeKJwxaBRoif4sv7mZ5fSAgGhFVzQFzTCaW+TG+SxGAdLHN+2sCRbLVElGJLDNBdQlvWSjpG1MlBzam8bMxv6NZkZQuNFjRBgi3MsmIKjtrTXl+RETp74BB54lwvlP6llUUSU1jbZ8dFCGzpN8lr9eN2owNFktryLBcw1wmcCfyVd/yXef0Vj7e+OL4vUcTm0CJ91Lwy+NQ=";
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
        }.runTaskTimerAsynchronously(Main.getInstance(), 0L,100L);
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
            player.sendMessage(ColorUtil.color("&e[NPC] YumeGames等级向导&f: 你的&3YumeGames等级&f代表了你在服务器中的游玩进度. &7[1/&a2&7]"));
        } else if (round.get(player).equals(1)) {
            player.sendMessage(ColorUtil.color("&e[NPC] YumeGames等级向导&f: 通过游玩游戏与完成任务来提升等级，然后前往&a个人档案&f领取等级奖励. &7[&a2/&a2&7]"));
            round.remove(player);
            player.getInventory().setHeldItemSlot(1);
        }
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
    }
}
