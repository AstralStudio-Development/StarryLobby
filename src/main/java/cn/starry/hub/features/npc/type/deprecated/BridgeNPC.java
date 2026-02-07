package cn.starry.hub.features.npc.type.deprecated;

import cn.starry.core.utils.BungeeUtil;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.utils.toolkit.citizens.Skin;
import dev.jnic.annotations.Include;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/24
 */
@Include
public class BridgeNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "bridge";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("&e❖ &b搭路练习");
        lines.add(PlaceholderAPI.setPlaceholders(player, "&7当前有&b " + StarryLobby.getInstance().getConfig().getString("total.Bridge") + " &7名玩家正在游玩"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"), -34.5, 58.0, 176.5, 90.0f, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        return null;
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player, "G_Bridge-1");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.SANDSTONE).build();
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

