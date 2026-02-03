package cn.starry.hub.features.npc.type.lobby;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.utils.ConnecterUtil;
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
public class PitNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "pit";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("&e❖ &b天坑乱斗");
        //lines.add(PlaceholderAPI.setPlaceholders((Player)player, (String)("&7当前有 &b" + StarryLobby.getInstance().getConfig().getString("total.ThePit") + " &7名玩家正在游玩")));
        lines.add(PlaceholderAPI.setPlaceholders((Player)player, "&7点击游玩"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"), -48.5, 28.0, -10.5, -90.0f, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTU5NDI2MTY4MjA0NiwKICAicHJvZmlsZUlkIiA6ICJjZGM5MzQ0NDAzODM0ZDdkYmRmOWUyMmVjZmM5MzBiZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJSYXdMb2JzdGVycyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lYmJjMTViZjU4YmVlZDdlZjZjNmY5OWFmNDU0YjRlZDk1ODNjODk3NDhlMGQ4YTAyMzY0ZTg3Zjk5NWMwOTNhIgogICAgfQogIH0KfQ==";
        String signature = "PbSf74C+Npj8rD8ckxaRrZBc/bjzwtU8rWcwJ6nwJqWWfB3dMU6CStD/qz12yfDItXaoXpX9jmJVBTPVo/KaqWc+g65k9JgsOtvNBbh2dOL0VdCuCqrHNJgPBqlzmJdxp0xLc+cFkudEu5c1C5RapPJ9zM2M7aHRakRWFJYmjz2WnAxcgX9v+9/SOWKp/3Wy4e1KOCb7MGgiVtkZSv4K7VebyutZvOcgmOCs1WF7ZyVfOyrPA5OxyIxOBX4TI0lUi6RKzbQ3zbnCNnam3GHiveMBUE/QcaBC88VoZ4T3YTpsmR3r3n3hLF0kzzoqzTfv8Zg1A2yNCZHxDmUeiYwpsSHdXLBwuarYU6fvp0CJb2GsrY0mzV6cEhPuZrre5f9I6XXfwy6jtmVZTdD/xOtX7Ax47evmnBi0z+blKMy3Df+/rfOJGzK0HZRL06DBpwJzscEhj7nYD9uW0dh7wZgqnDeuS3dpxl4f9UkiTMtGzafqWSLRVnPtynfVAjqeVTEJIV4ivAQ+pYph7NHzLzQ+ptXFtw2nOC0p/qaiStz2k1QNrrv1K0fvSWwA+ZJLCASkxQxsuHdnycp1auGAdMxuVh9lfoycTonsutSrhTEPlhv/byxqZt/KSuW09E+mzTTTHLKBr4Le5Stu62zAZ2mrM094jVYU0bj+yyHAUL0MZIo=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        ConnecterUtil.connect(player,"ThePit");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.IRON_SWORD).build();
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


