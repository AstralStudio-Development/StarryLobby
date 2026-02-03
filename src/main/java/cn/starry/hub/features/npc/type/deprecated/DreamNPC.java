package cn.starry.hub.features.npc.type.deprecated;

import cn.starry.hub.features.npc.AbstractNPC;
import dev.jnic.annotations.Include;
import me.clip.placeholderapi.PlaceholderAPI;
import cn.starry.hub.features.npc.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
@Include
public class DreamNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "dream";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&e&l点击开始游戏");
        lines.add("&6梦幻模式 &7[v1.4]");
        lines.add(PlaceholderAPI.setPlaceholders(player, "&e&l" + "%hub_BwDream%" + "名玩家正在游玩"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-5.5,68.0,13.5,115,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYxNjg2ODEwNzY2OCwKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85NmYzMWJhNmI2OGEwZmQyMjNiM2UzMTg4NTEwOWJlYmRkNDNhMGI0ZjQwNzE1YjAwZGNlMWEwNjM3NmI0MjRhIgogICAgfQogIH0KfQ==";
        String signature = "eKuYfkdZAn7TXoBm469fOoVR9Z7NANyxSiVa0DEekI0RSTrJp2Y3MOBb3WweqlUhQ4+ad70k2PLYwPdyDJfyfNQ6ygXREwer2xvRiuKfXw9vewM0lT4cxpaBWs4TJS9Xy9mOjp+1+VjBrbTrPBObW5adpFT9i/x+Sb5950JyMAcuOtytFgObKbzabyDGlJusm/9bzOHyKwrPHaiNlbb6FdhPQomFLgQYBuMeJGwreeRLiMA7DY6OJcbGRFoT2fupSBJpBT8bQ0mB5bPI4KhBAB891HjQjqBiQVolRjmDwNAtLaRwdUvFgKbxEixFU5B+2KowKwzV24eeL01vOqeeSSmu2PQc85zxEAlfHLIN2KtvyKPJHOr1dyOrgsx6bFLUmtJHeTGKoLUVDcAHVrovhWnSl/QK7lLFQJsOa5fxp38qk4Z+OpMFet2CuTRLr15ttTKjomhSjrdaBaNQYYxMNlhLJDfUiROuPfn/l/CSxtzBMe5VyK/IGx6k1VLyq0igoKFprlAlOh5ZjOeMVZLurT17uISEpChY+fAJpgOgGMA7+1HH4diX0VFwAy6Fx35JRqhC9qNDgao/tmUBnvpOvQpu5TJV7XRxI4J378zo1zFEKEB9f0l0gqYCrpkLKTibJVWivrgm3NXmUnh8pWzJv6a0q9eTTHVYENv5zrtG+Mo=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        Bukkit.dispatchCommand(player,"dm open specal");
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


