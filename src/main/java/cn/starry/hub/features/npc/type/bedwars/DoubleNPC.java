package cn.starry.hub.features.npc.type.bedwars;

import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.Skin;
import dev.jnic.annotations.Include;
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
public class DoubleNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "double";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&f点击开始游戏");
        lines.add("&e❖ &b双人模式 &7[v1.4]");
        //lines.add(PlaceholderAPI.setPlaceholders(player, "&e&l" + "%hub_BwDouble%" + "名玩家正在游玩"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-13.5,14.8,23.5,-180,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYxNjg2ODA5MzcxMiwKICAicHJvZmlsZUlkIiA6ICI5MzZmMTA3MTEzOGM0YjMyYTg0OGY2NmE5Nzc2NDJhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMDAwMDAwMDAwMDAwMDB4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I3NGI0YmZkYzUzZWYyYTkyNmE3ZmVkM2Y4M2RkN2UyYTEzNWFjZDgxYzI2ODJjNTk1YzRjZDA0MTc2YWQ0ZTQiCiAgICB9CiAgfQp9";
        String signature = "t7pfpi1gLZ2miRowaYjxF+4qedigXeBj7dRwGdDDcf1H9Ibg0JsVEOWjndm05vbc/rFlhXQi0+tqA1+gdIi2orQhJ0OPtQX9TTRkUqChlFFOQtOUndWzt+sXQFlpQ0bT1vjC/U7Vkmud976lLizfGBhTCApE5pn4DKSkuzIfgjvJlmQ5hFQDRt0r8EqqbJy8zlI6VfV71e3e0DF3KiommmuD5AMM7HfJBVUU0PuqaAYGguHJrkhTi9yHueZ/oJEdX4mKHZVKDLt0dbOFltXg7cSfCylxAq9u89OlV1TJSBDrhp7/I9q288+Snz+ghG6kjGJ5Ad4NI4YJgegaxoyrhompqJNFu73UOsN/AAtmerfjoZu4RBEECHP7jicgqxks2U75lO5DrKVNdxtlpJuCHvH6uEatg3AKbnEOy0UQZ1qQQn3rTfw0C60QyDgdoSChsuYoe3japPsr4jzi71xn4jyJBuG6M2Vco2YCRRamHl6w0yPg1CjtyXjdjsuQHZQU5oAeLEOaleKoMCJMfrcEpaM0L2Dgnhxp+pj2K0WXyMbR3H8VjZHDB/WyF/tSo+1sA8Pvk+MLUzoZjyO05khFsRgCtRRhl5vwX2K87GcF5/qNkxTxm/EyzdLBQ9UGws1Y4AkBzmUcBX9KFSN5Moz4B5aNcjcSt/0bdRIM4d/VQow=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        Bukkit.dispatchCommand(player,"bwmenu 双人");
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


