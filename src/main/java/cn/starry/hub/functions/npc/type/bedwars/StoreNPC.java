package cn.starry.hub.functions.npc.type.bedwars;

import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.Main;
import cn.starry.hub.functions.npc.AbstractNPC;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
public class StoreNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "store";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
                lines.add("&bYumeGames Store");
                lines.add("&e&l右键点击");
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),12.5,69.0,21.5,90,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwODAzMDgyMjUzNCwKICAicHJvZmlsZUlkIiA6ICI3ZGY4NmY1MWFjZmI0MjQzYTkzNDQ1OTAyZDEzYTc0MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNclJpcHRpZGUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmZjYjMxYmFmNjQ4ODExM2MxN2E5NTkzMzhhMjI4OThhZDQ5NjFkMmYzMDg0MzU3YjRkMGNkODA3YWRkMDg2MyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
        String signature = "qqQ6kt3aPvRvxN2c2WeUEcz/niPnh76Ak07q03OshcbtcGZ+S5HbWNYnaApUBmsc2dGC/KYgWTg610KBJybHxBdwC0D4kO6TcM4dUWNzWgwosij+qv96bXD5Awryelot1DW6i4f0TuzPcIH7jDt4iHxbBXEWn1KLJ35yL+wM15vfsUEMtlm5B+6kK8kWtpg7YWKoZiez50LtkXeqQscYRdiq4MWLqNBs2WtDUwpSuEnLzzCyBJkWeioOrenTYMe02RLnLSvsK59k47hzuGq0KsSfJaI3oK0cROBVUpXNXh9aPagcSVjyX4RgCoEbdcZBH8k+V2QY0KtqSF4s4B7PxUVWDhk1F8COo1QiLCB17FgOY5jdGpjBhthyXmuxO85827WOk1MiaJpR030fjQ3wrg2+GNjArcqDKPqnlBF+cnrf2pnVwnh+VLenPmBUXEnHT17kOHd1H4M2scm7eK/0dBU7JvcPouqIfYSXv2aewQHnHm2xOpQamJp6qtmuoEIMm6ED7lnLK2KCr7XIzVAQqTVdFNEeCs7+eZiM9HPE+xc9pCGaYNgHBrH4Wdfq4zFyoz4CkB6JTU8Ke80wQei8Mib3Kh6uQgodoKCW34nXczuEYLZk2HQy4lh6Gllf2yu+EH9frMTs3OHbqmaBFQfM8RJM53c6CqRgeAC97wScZXc=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        //BungeeUtil.sendServer(player,"G_TourP");
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
}
