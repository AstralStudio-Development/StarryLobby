package cn.starry.hub.support.placeholderapi;

import cn.starry.hub.functions.client.LunarClient;
import cn.starry.hub.Main;
import cn.starry.hub.functions.activity.springfestival.Y2024.presents.PresentsManager;
import cn.starry.hub.utils.RankUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlaceHolderAPIHook extends PlaceholderExpansion {

    @NotNull
    public String getIdentifier() {
        return "starrylobby";
    }

    @NotNull
    public String getAuthor() {
        return "Starry_Killer";
    }

    @NotNull
    public String getVersion() {
        return Main.getPlugin(Main.class).getDescription().getVersion();
    }

    public boolean canRegister() {
        return true;
    }

    public String onPlaceholderRequest(Player player, @NotNull String label) {
        UUID uuid = player.getUniqueId();
        switch (label) {
            case "points": {
                return String.valueOf(Main.getInstance().getData().getAchievementPoints(uuid,"points"));
            }
            case "client": {
                return LunarClient.getPrefix(uuid);
            }
            case "presents_own": {
                return String.valueOf(PresentsManager.getPlayerPresents(player.getUniqueId()));
            }
            case "presents_total": {
                return String.valueOf(Main.getInstance().getPresentsFactory().getPresents().size());
            }
            case "rank_format": {
                return RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(uuid,"rank"),uuid);
            }
            case "rank_display": {
                return RankUtil.getDisplayRankById(Main.getInstance().getData().getPlayerData(uuid,"rank"),uuid);
            }
        }
        return "PLACEHOLDER_NOT_FOUND";
    }

}

