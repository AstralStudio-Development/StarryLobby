package cn.starry.hub.functions.npc.type.lobby;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.functions.npc.AbstractNPC;
import me.clip.placeholderapi.PlaceholderAPI;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
public class ArcadeNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "arcade";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
                lines.add("&d&l漏洞修复补丁推送 & 新地图");
                lines.add("&b街机游戏");
                lines.add(PlaceholderAPI.setPlaceholders(player, "&e当前有" + Main.getInstance().getConfig().getString("total.ThePit") + "名玩家正在游玩"));
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-27.5,93.0,-13.5,45,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTY0NjA3NjU4MzY2NCwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MTQxNDE0MWgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VhMTRlMTI2YmFhNmY5ZGY1ZmJkNTI2ZmViYmJhM2M2NTgwNzI3ODhhNmYzZjEwNzMzOTczZjQzOTg4NmZiYSIKICAgIH0KICB9Cn0=";
        String signature = "lZ9zbsF400WuPThas3LEjdry82Mwr/TrCCtRKcruSpSDiBoKkOmJp6g0D7PFOPvOLT15p9uRbsrlIekgBOQVjtKoFrETIO5SbI4ubBuNwqJL14ENODsQ6HGeXTBjL2jpNTC1eT/EEdCQvd65bMf+D4SdPiywFijFwYiI665OuRaWnK4H0GT56k77EwKU0WOtGYN/cPHRTaalvbcsFxauNY5SfU+9q/HbvUIfuY2SJxKupCPEF/o7TLR0cMgpX1eg+5D+Vn9eNwgm6Mjj7slF4NEjQsuP2nV/sPy76tKAnrrL6rNfF6qRnogpb8WHSk4xCcCFBAgRhlxgmYS1TWu9DfMEBd4S+X4CeqpiTWah90rnJDXUrGAx6gBgbj5xwTLgcK2nm8d/gbMmn7Us5D/7nizdD4Wq5X3EZfnkLf0og/pM9NL/Y4BKmAJP7VzDVfnfsddcQb8mpIP+lD9Q5D9OYEFCAuF6tW2hi4dkiImmJNpfrM2CmXV0jHq305Y0/HZa4F9dyLDR5OnHWWLx3nkflkKJLmQGcT7wRELh5N2mKDGs6x+EXXm7x2f+7IE4qWknRfS/YwYSlxuEnLexHoN3j+ml8Cz1hbOVgXe21jb8JFEXgTlNQHiXVp8+buQ9x5G/4npIJzKkKFLOEYZxRqd85eM1QwfpTuJob6kNxDcd0MA=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player,"G_ThePit#1");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.SLIME_BALL).build();
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
