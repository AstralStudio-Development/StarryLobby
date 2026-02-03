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
public class TeamNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "4v4v4v4";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&e&l点击开始游戏");
        lines.add("&b4v4v4v4 &7[v1.4]");
        lines.add(PlaceholderAPI.setPlaceholders(player, "&e&l" + "%hub_BwTeam%" + "名玩家正在游玩"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-2.5,68.0,5.5,100,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYxNjg2ODIzODA4MSwKICAicHJvZmlsZUlkIiA6ICJkZTU3MWExMDJjYjg0ODgwOGZlN2M5ZjQ0OTZlY2RhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNSEZfTWluZXNraW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjUxMmFlYWZmMjJkYzYyODZiMDkxOGIyYmI4OGMzNWM4ZjcxZTk0MTMyMzIxOWI3NTZiODE2OGI3OWZjNDcyMCIKICAgIH0KICB9Cn0=";
        String signature = "tQBE08RCcC7KXz758VAUxJRwHRmITEHre3VmUcyxtmluwNzpAYhm/NcJXwhPrw3W+sY4gnOWbWc5deoc8tAiTT43tJWbqsOMUEZ2ECS1mBfpJVCpO8A/fboJF0T8WWkW3Mi3j607kY5sfzrVmn1jw9BoCit0SXXXpM9RLuglWkoXQ6i55pTJCcwyXx5FjjoucdvBwlmTTZ/HYCLaf75KeyPZrDmu7UfqWpE1aUmXVv3cvRmB80dCIQHh8WZX8UFHt9X/gOOwolczt8bT+AuOQMoJ4wZ415Iw07dOI19ZgPsn8csrKmmScub/4r1LbeKr3MnyHYdF4PSbCERWaDNZfX6CwDYqxQJufimnXBRaVl1oT7hmhB8ywy2/49LW5nqw4r7QT03XfqkcCEfEVyTnU5FfzukPTshtZWui6Z5Kvd8hF5OHM07SWf4xkioPPZJ1P40I80xVx88rnrsDWSWo8MiAJKsjvRus+wQPhysdR4P6ZjMql4kFhzmWagnGRAPlsKR77b3hdlmCv2S530nIgkN0FzyOlA4/XB9QFwESPL+j4HaAtzcJx5xvhpF69LYsdd8yvf5e6CS2SiZJLv+HnX6gA5DUXnVi4LbRxYu3zbrBUAVFJmsIX8G/IfB6YVRheDkOWNBmhN29bxjfjCDSVjfgoBIIZl00VhrjDq9kXWg=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        Bukkit.dispatchCommand(player,"bwmenu 4v4v4v4");
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


