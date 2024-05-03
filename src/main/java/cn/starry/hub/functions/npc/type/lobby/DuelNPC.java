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
public class DuelNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "duel";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
                lines.add("&e&l点击开始游戏");
                lines.add("&b决斗游戏");
                lines.add(PlaceholderAPI.setPlaceholders(player, "&e当前有" + Main.getInstance().getConfig().getString("total.ThePit") + "名玩家正在游玩"));
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-26.5,93.0,10.5,115,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "eyJ0aW1lc3RhbXAiOjE1ODUzNzk3NzQ3OTIsInByb2ZpbGVJZCI6IjkxZmUxOTY4N2M5MDQ2NTZhYTFmYzA1OTg2ZGQzZmU3IiwicHJvZmlsZU5hbWUiOiJoaGphYnJpcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UwNmNmYzNkNDNkYjEyZGEyZGJiNjAyOTNmMDQ4MjE0OTRiMDAyN2IyNzcxNTQ3M2YxYzE0ZGJmNTMwZThhOSJ9fX0=";
        String signature = "KengCfmMS6Zd2dO3ZMVZ4Oekr5w6mdieC7qlyG1sBpVdu3LAKIOzFMkYmiVhGVKvROWcdrzmYsY6nNv0qLt/Gx7oea4PRM9trZML86EnKB26akTQk43rwMLKQBRHp20lZB6icxS7V6QcOV/kr+uLGghnfzOrFzS9n+Xz4YT1ld6/6bVTxBoj9Nlsh/ZC+L74MACrcJ9k8piiMjON97VVnV0F8yn5KBTIMyyS1zPEYlb2eUwtrt7Lc8I6DUVwFXgnNmONmVotfUtFkEi4znsfFiQdhVmGz4pZz8Yf6rxFV1Qciek55WVEpa77hYMk4Y092PVyjoP3aOOkKMuDrVa4KyF+4YpODjjAdicfIL4y5+VTAKCFBSbfun2Vz+LYzDKEOK5gm3xI6RhA9LlQuWpOl7ETxHZWW7rcqK2V77bV4E3dw4WX9LThHjSZXJ7FchubDtEBWZUOXDKBsY1CoifuaUNN5RveRWA59XJxSft/sXtEcRSJk504gB06wKKLU6wkIadtaN6cAQmdF33KiAa1zL7dRVikN+wQeVfj3NE7cWQnps9pYya9Edgo9ISsXEYyjxdAmy5ii1q/uSJZCmdzLupvr1Q+91cKhQ0IcpblKE3Kx0+sQLQ90NQptVY8g2Ll6rangBUDvnMo8SqfdBoXfH8OHLUwUEhlpL+lyGBIorc=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player,"G_ThePit#1");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.FISHING_ROD).build();
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
