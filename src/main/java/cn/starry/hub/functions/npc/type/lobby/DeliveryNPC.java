package cn.starry.hub.functions.npc.type.lobby;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.functions.menu.profile.DeliveryMenu;
import cn.starry.hub.functions.npc.runnable.NpcRunnable;
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
public class DeliveryNPC extends AbstractNPC {

    private static int value = 0;

    @Override
    public String getNpcInternalName() {
        return "delivery";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        lines.add("&b礼包使者");
        lines.add("&e&l右键点击");
        if (NpcRunnable.c == 0 || NpcRunnable.c == 2 || NpcRunnable.c == 4 || NpcRunnable.c == 6 || NpcRunnable.c == 8 || NpcRunnable.c == 10) {
            lines.add("&c每日免费奖励！");
        } else {
            lines.add("&f每日免费奖励！");
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),153.5,128.0,248.5,30,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwMDU2Mjg5MDM1MywKICAicHJvZmlsZUlkIiA6ICJhMmY5ODQ3MmIzY2Y0YmMzOTM2ZWNiZjBhNGZmMTcyOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZWxpdmVyeU1hbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mOThiYzYzZjA1ZjYzNzhiZjI5ZWYxMGUzZDgyYWNiM2NlYjczYTcyMGJmODBmMzBiYzU3NmQwYWQ4YzQwY2ZiIgogICAgfQogIH0KfQ==";
        String signature = "w5kMzLd5NxfQk0yjPETQMgI/aCUZIg6mHkiZeHcv0BuBhXNamghbfou03h/pLgJPcRV+rtdnnjeWydBlL/JNwvAe+5HtfhOCSgNd7SB8lQF1DitkbzVxiL6IBO7UY0XcGHoF1QBBZcoJ5Cfc0hjPDcmsLOj0SWs5ehZ2EO5eMb0PS9EoMJSUYeQi/TL90oaxHkmj7Rw+Ix8QZ5E0Ef56io22BKjnuwjlKZY+7H+6fQV6zylcbiKZvPMWph6nMHLJawjSvrhpu8r9bu56HEvdKWAALZPQ/14SVenJ3CPEGW3OJIqcAgh+Sw6AQvMakDB0rp3VKk6Lh3D94xOmX8d4QErNwyb3Fd9QU7bLTyo3dE7K+Hg9hi6DeHL+9dszIYKoXeIrwQGrDfDtjUeSpWvFZTqpy+QcCAE4UuRWDnKwp2Prv8s8/DnZhoCaSGoM5tU+zkoJS6P1MJTWvQvXW1dOKUyJRdmxzdU9JEZsVX7+bz9SnL5zziGsSgG8sMoYNXQYrzKq5kpx+wC5p/gb0MCgK3mwHD8wl2T+1ScHk6M02yJSGjvsikKNBS14mtmyQjbTvIQYHjatc3D+gICoal+wnYckJsiPksw/ScPWv07jiMSZ60gI0P0ScTvwFGQ5rFM3BOFlw0LzbRfS3TJ9EUsDWSoVB0EbI8TbmtAxH0we6sk=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        new DeliveryMenu().openMenu(player);
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
