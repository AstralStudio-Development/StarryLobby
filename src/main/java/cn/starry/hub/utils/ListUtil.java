package cn.starry.hub.utils;

import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Starry_Killer
 * @since 2024/2/9 1:25
 */
public class ListUtil {

    public static int getOnlineCount() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        return players.size();
    }

    public static List<String> getPlayerDisplayNames() {
        List<String> displayName = new ArrayList<>();
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player player : players) {
            String rank = Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank").equalsIgnoreCase("NOP_DEFAULT") ? RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank"), player.getUniqueId()) : RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank"), player.getUniqueId()) + " ";
            displayName.add(rank + player.getName());
        }
        return displayName;
    }

}
