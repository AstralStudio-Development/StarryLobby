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
public class TourPNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "tourp";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
                lines.add("&6❖ &b幻梦双笙:羁行之旅");
                lines.add(PlaceholderAPI.setPlaceholders(player, "&7当前有 &b" + Main.getInstance().getConfig().getString("total.RPG") + " &7名玩家正在游玩"));
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-25.5,49.0,-3.5,-90,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwMDU2MDM1Mzc4NywKICAicHJvZmlsZUlkIiA6ICI5ZTQ0MTdiMzczMjI0Y2I4YjdmZDJkZGVjZjFkZmEwNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDbG91ZEZvcmVhbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kN2MyYzA2OGJhMDQyMDY5YTI3ZWRkZjgyNjdmMzJhYjI2ZmEwMDdlNTIzNTM3ODQ1MGM3MjA4OGY3MWEwNGNkIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0=";
        String signature = "kwp3YX+pANNdww6LpZs5C+gX3y3HFFWaShTTtJxVEmBxhJBm4bydZ7kLwXIznarCPaSxFJFdD5ZhkYigAz/RZHXPlbIcuBNLc1/2MgJePgaGctn8TBRHllx4evVN56KOD0y79EVxplxDGpH4B045SUNNkDJE5m0u4axQL5PQBRaOy6qypjYCJQi52HQ2Dzapcy5H4fU/uJZ3FdoCslf1Vhj6TXDh404V/MvMakoesZ5kP99XFLEqdK6s/T/mhyGlAmCeqWZvWy+mPTyM+JIgtjEyvEVvi/iZrdbZJUOTNcOX5Sm439s8FK78LFB7JzO7n7CLLMFr1a3JuRmA8xcPxYj5L76TAHmtbesWP+TrkFCJRpSTGcDhswtL85ap81XpdnO29X/9cSM2l/+QMQp1gvTsSIMxxMaUqa4Elyf7Wr89UbkAqxVwIFT4oEPCf3HxEH07pPudvHYhaV2vtjDygasnOnUd8u6jI1eNsEzGQODVRNYiINa3uJ6L+lDwoXlrjagsl4q2PRAXdMox5WmnWp3LxMGzwf8Hua6vIwJmxqB+y1W8aslPQ95ZpwKu82RlB+BO4iMvD1KfZL9RgW34WNKI2L6qTnGK6R05gXUOQEXCL9fwpy7PtTW4R1caJjNE1ZCwMyy6/1vyKUtIqdiHkFmVf0V8CBJJ83PmArMzLYo=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player,"G_TourP");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.BOOK).build();
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
