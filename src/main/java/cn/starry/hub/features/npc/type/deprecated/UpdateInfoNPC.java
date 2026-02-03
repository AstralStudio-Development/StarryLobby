package cn.starry.hub.features.npc.type.deprecated;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import com.bnstra.npclib.api.skin.Skin;
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
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/24
 */
@Include
public class UpdateInfoNPC extends AbstractNPC {

    private HashMap<Player,Integer> round = new HashMap<>();

    @Override
    public String getNpcInternalName() {
        return "update";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        lines.add("&3即将到来的更新?!");
        lines.add("&e&l右键点击");
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
                if (round.containsKey(player) && !round.get(player).equals(4)) {
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
            player.sendMessage(CC.translate("&e[NPC] 更新向导&f: 接下来,让我来带你了解即将&3更新&f的内容. &7[1/&a5&7]"));
        } else if (round.get(player).equals(1)) {
            player.sendMessage(CC.translate("&e[NPC] 更新向导&f: 首先,我们对&b起床战争&f进行了底层代码更新,修复了大部分旧版本的漏洞,同时,更改了死亡判定,使得游戏体验更接近&eYumeGames&f服务器. &7[2/&a5&7]"));
        } else if (round.get(player).equals(2)) {
            player.sendMessage(CC.translate("&e[NPC] 更新向导&f: 同样的,我们也对&b天坑乱斗&f进行了底层代码的更新,并更新了部分附魔,事件. &7[3/&a5&7]"));
        } else if (round.get(player).equals(3)) {
            player.sendMessage(CC.translate("&e[NPC] 更新向导&f: 顺带一提,我们重新调整了&eKb&f(KnockBack),这个Kb配置将在下个版本启用. &7[4/&a5&7]"));
        } else if (round.get(player).equals(4)) {
            player.sendMessage(CC.translate("&e[NPC] 更新向导&f: 最后,我们将在春节来临前上线此版本更新,同时开启春节限时活动. &7[&a5/&a5&7]"));
            round.remove(player);
        }
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
    }
}

