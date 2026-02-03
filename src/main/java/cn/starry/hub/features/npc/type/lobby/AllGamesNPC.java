package cn.starry.hub.features.npc.type.lobby;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.menus.selector.SelectorMenu;
import cn.starry.hub.features.npc.AbstractNPC;
import me.clip.placeholderapi.PlaceholderAPI;
import cn.starry.hub.features.npc.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
public class AllGamesNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "allgames";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("&b所有游戏");
        lines.add(PlaceholderAPI.setPlaceholders((Player)player, (String)"&f点击浏览"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld((String)"world"), -46.5, 28.0, -2.5, -90.0f, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcyMzM5OTIwMDE3MiwKICAicHJvZmlsZUlkIiA6ICI3ZDcxMzY3YzMzNTI0NWY4OWUwNDA4YzdjZWZjNWIwOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJLZWxpcG90XyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MTc2OWNiMTc5YzQxNTgwOTEwOTAzMjQ4ZGM0ODgyOTEwNGUyNTBhMDliZDg5MmNkMTMwOTNmYjgzNDk0NDk1IgogICAgfQogIH0KfQ==";
        String signature = "nPXT95qlVL1xWNn5hO7yKWSM+AEbsNi2NZS1/780P88BK+Wixnj1exUWbyMe7YpyZkaPaYvgmxI93hrPmJlfsvYCxP9HMvQTV4mEUY139hjgj5hIhqy/0FIhHMI0a33dvxZUUU5RKD8hRSseHNTi+FlpN8ZD/kFL/ZP1kiDNj13+oE+INc0+ahpPH7WtE79M9fDf9q1Yc8fPLb4oxoAh9PkDuUA2GYoyloG+dQU0uj15T9TVu7S1AzNMrw1YN6vrnmsS34g1vnk+j8op0R6Q8Ctf5XFmuVJB2WRzIJ90vmKhq/HMzNRJXjKPUJU32/zqp6xhgT8nMDHLJ5Py79uVLAS8Lq4/b0LSS3MnumbUSlfizYys7Ihlv4CjbLE3WKX8IX5W2B4Ar370gSHGc4oMDd9lm8pNPHRKh3UlCNsaBuG03qF7rNnePab1KSNRxKEDTFi+0utVpgtjTK4yO+rZdwe/hzZbHCYMe4yFnd/w+Cpsu/IqWukYrMqa4p8/i9ILb1wPfp9LqWYgLP6jqf71X5IeLuXR0eyf6kYBP/A6YxQGtBqrrvqdQ1qgVPYQbkldgZa19dVI4lRhK4TNES4j25FxeKBZlclFpqH4ijHnpZomab9KGAoPMsPIiR4Utg9X2vmG3wVCEsC+7iYekjq4Hng0zAl8xkcYWp9KCLGr5g8=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        new SelectorMenu().openMenu(player);
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.COMPASS).build();
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


