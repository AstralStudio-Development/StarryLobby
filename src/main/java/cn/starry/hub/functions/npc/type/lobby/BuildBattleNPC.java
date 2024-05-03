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
public class BuildBattleNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "buildbattle";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
                lines.add("&d&l漏洞修复补丁推送");
                lines.add("&b建筑大师");
                lines.add(PlaceholderAPI.setPlaceholders(player, "&e当前有" + Main.getInstance().getConfig().getString("total.ThePit") + "名玩家正在游玩"));
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-29.5,93.0,-17.5,45,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTY0NjA3NjYxMTY5NSwKICAicHJvZmlsZUlkIiA6ICI0ZTMwZjUwZTdiYWU0M2YzYWZkMmE3NDUyY2ViZTI5YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJfdG9tYXRvel8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMxZTIzNTZmYjg5OWFhNzVhMzhlYzdlM2RkNDM0MDkyZmJjYzc0MWQ0YzQ3Y2JlMzMwMTIzYTM2NzIxNDY2IgogICAgfQogIH0KfQ==";
        String signature = "fq6QsCKREUgyJByzN2VLkPfsT04fl+rbkraZI405PA3mlG77+7pGBtGnspumbcpU6IrXkKGKWsObfw2iYV9oiTbuKnMs6iR+YfyCQKYSr3T97K0zpWbv+s+7H5bUN/EjICmz0ilZM6Ifk9z+QIJZPw0gp8JB7RSwDNg7bYSBt8pGielQDwSG92+qPh85BHMBRiIJUq5lGuytXMbpG29ENKSH+lEF+J7h9bNg7sDfxrw1Wkc5UbxKUnyVaRrMwRwi57MLXrcxQOG75qq/Td5JEQtYeN89g9bEZoGrDLSlfS9VQMqUQ9wbpqnTYQeRIYXR37vBX862OQl0zy9vhrcdnxFQAc1nx2SY7i0vExAhYGYwlxfQq1boCzOjyyCDJSqn9RI01OB41AfWr/x8FuL04Z9/i9R0VVnkb5DzZaRQEqgkoWvimZMG5OTzVrUEcmVb9d5PFfC0SoAUjtpSlkby0y0Hh1NrS4VIAMr6z6uB6OHtTMEumU3wWXGngJHk8bztjCWNTo9e88AkPn0+78R8BnOMS7oFjG3rPuowRimQ8wM5HJ+YHJsJSvZ3yoONR4iW2SDF9O2b6TZZ8v5itR/H4Be0DQcc9MLpkyszl7q/7O/7PPhT307ZWoSyXja3K75W7ieG+0P+BjiltqOgoazU+6ck7blL3//A/IVid+gBM+w=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player,"G_ThePit#1");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.WORKBENCH).build();
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
