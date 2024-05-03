package cn.starry.hub.functions.npc.type.login;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.database.MongoDB;
import cn.starry.hub.functions.menu.agreement.AgreementMenu;
import cn.starry.hub.functions.menu.buttons.ThePitButtons;
import cn.starry.hub.functions.npc.AbstractNPC;
import cn.starry.hub.utils.ColorUtil;
import net.jitse.npclib.api.skin.Skin;
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
 * @Created_In: 2024/4/23
 */
public class AgreementNPC extends AbstractNPC {

    private HashMap<Player,Integer> round = new HashMap<>();

    @Override
    public String getNpcInternalName() {
        return "agreement";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&a神秘人");
        lines.add("&e&l右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),30.5,86.0,-2.5,45,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJpZCIgOiAiZTJiOGEyZDE5ODVhNDRlOTlhMWQ3NjNlZTEyZjAxZjgiLAogICAgICAidHlwZSIgOiAiU0tJTiIsCiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmYwZGFhNDY5ZDJmN2NhZmI1Yzg2NzlmNzM3MTg3MzRiZmU2MGI4OWU5MmY2OTQ4ZTc2MWUwODQwNGNiOWE0NiIsCiAgICAgICJwcm9maWxlSWQiIDogIjE5MjUyMWI0ZWZkYjQyNWM4OTMxZjAyYTg0OTZlMTFiIiwKICAgICAgInRleHR1cmVJZCIgOiAiZmYwZGFhNDY5ZDJmN2NhZmI1Yzg2NzlmNzM3MTg3MzRiZmU2MGI4OWU5MmY2OTQ4ZTc2MWUwODQwNGNiOWE0NiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfSwKICAic2tpbiIgOiB7CiAgICAiaWQiIDogImUyYjhhMmQxOTg1YTQ0ZTk5YTFkNzYzZWUxMmYwMWY4IiwKICAgICJ0eXBlIiA6ICJTS0lOIiwKICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmYwZGFhNDY5ZDJmN2NhZmI1Yzg2NzlmNzM3MTg3MzRiZmU2MGI4OWU5MmY2OTQ4ZTc2MWUwODQwNGNiOWE0NiIsCiAgICAicHJvZmlsZUlkIiA6ICIxOTI1MjFiNGVmZGI0MjVjODkzMWYwMmE4NDk2ZTExYiIsCiAgICAidGV4dHVyZUlkIiA6ICJmZjBkYWE0NjlkMmY3Y2FmYjVjODY3OWY3MzcxODczNGJmZTYwYjg5ZTkyZjY5NDhlNzYxZTA4NDA0Y2I5YTQ2IiwKICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgIH0KICB9LAogICJjYXBlIiA6IG51bGwKfQ==";
        String signature = "e4hW2x0/NyB4hTZqK2v/qpXfRVj94bXvMOvH3XsDL/RgOzHrsLWkpSh7qWY7NP0bUdsOu37F27tsidcg3F2vFJdh2qv2QFwQr705ht4taYlo1MGFQearMM7Pp7lu7O3yTGZ6gikq6sdW2BNW7I54KoEaNINvk7jh8rAQQbDmP7GfK/HIHbhgMM7hUEex4QfF65zdwr6pVsMv7DnI3ZCaHDV/9JnxepdH0tX9WdulpRsskoKfiklru0ZzovuI9jFZhI09Tk12rDr8BEXEs9RDWDgokkEpnzm2ZNs7eh4E1jbE7BpD6/whJYwWGpS3ssjHfR7aCfjpKQUzocm/9SMoNAPTJI8Bl3PDr5BeX0uQ36RfhqSYDXxemkNX7d7LcHpAl5axV63r2ECLF5RBzykENfjhSrmtMQ5uD7yyf/uW9QI5t/H7ahzdoaTBWLDDQ0Q4tQAKMEaxNKoAee55tiwD3UA1CqWq80Y6uhvClBKyrxPbcdfExoc7FzBX0G/CX6D338OSwcDp0Nzkzc4OdM5euehGsGJkEmFLrsK3wNrJBYm4HdJp2eFgx6PLRpYuNbvpTq4ooU3NiJs2oHw2LtkzNY9c0z3nyJHsD4JXM0yTIFoTyg2RuNE0mk221e3VZdlKtBhfXzTcgPFHCiXxLQLISKxCkSm2c3gmjIeGzbzJ7VE=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        boolean a = Boolean.parseBoolean(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"isLoginBefore"));
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
            }.runTaskTimerAsynchronously(Main.getInstance(), 0L, 100L);
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
            player.sendMessage(ColorUtil.color("&e[NPC] 神秘人&f: 看样子,你应该是第一次来到这里. &7[1/&a3&7]"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        } else if (round.get(player).equals(1)) {
            player.sendMessage(ColorUtil.color("&e[NPC] 神秘人&f: 跟我来,让我们前往 &3主大厅. &7[2/&a3&7]"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
        } else if (round.get(player).equals(2)) {
            player.sendMessage(ColorUtil.color("&e[NPC] 神秘人&f: 仔细阅读协议,然后点击 &a'我同意上述协议'&f 来进入 &3主大厅. &7[&a3/&a3&7]"));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
            round.remove(player);
            Main.getInstance().getData().updatePlayerData(player.getUniqueId(),"isLoginBefore","true");
        }
    }

}
