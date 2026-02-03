package cn.starry.hub.features.npc.type.lobby;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.utils.ConnecterUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import com.bnstra.npclib.api.skin.Skin;
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
public class SkyWarsNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "skywars";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<String>();
        UUID uuid = player.getUniqueId();
        lines.add("&e❖ &b空岛战争");
        //lines.add(PlaceholderAPI.setPlaceholders((Player)player, (String)("&7当前有 &b" + StarryLobby.getInstance().getConfig().getString("total.SkyWars") + " &7名玩家正在游玩")));
        lines.add(PlaceholderAPI.setPlaceholders((Player)player, "&7点击游玩"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld((String)"world"), -46.5, 28.0, -12.5, -90.0f, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzMxNzE2NjgwMywKICAicHJvZmlsZUlkIiA6ICJkNjYyYTJkYzMwNWM0YmJjOWYyZjExYjg1ZmY1NTU5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTa3l3YXJzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE2ZTQzYzRhNjUyNGU5YWU4MjdjMGI2MGQzOTI3ZDU2YTVjYzMxNjE3M2RlM2IwOGEwYmI0ODYyMTVmNGMwODYiCiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2U3ZGZlYTE2ZGM4M2M5N2RmMDFhMTJmYWJiZDEyMTYzNTljMGNkMGVhNDJmOTk5OWI2ZTk3YzU4NDk2M2U5ODAiCiAgICB9CiAgfQp9";
        String signature = "UOz54T1VcuG9tD4Exf/YaP/Mso6aK4SuSGYfDxzX1OjxJB842h7x1GCYOolio04TNdJURkgWJyPB27PYayFiN26TJUiuFMzL2ZU58FqxdEE9RBSYkhNfhpbIQ6YRUi0Y2gyOEvEeZR6JCf591xu67oeMtjr2VfU3mS921BlK/rh8AjNNBO/LyUzkUGwHXHYJsDJcE73I/NbE1+4I17mCB9XTcx20ihIHmVU/T3gzG9zDzblLeztuY+zPH0EAK4CxI/5zK3yjsYA0qxe4qs/cJZ2Xo11Hr7knx8XOlBhQlNtgSDk1QXrOtT4qUlc8OTdjbrDb6kBqRVtIfqlkHtC1p29vofpheLtknSNswTvttnWWCQim/UsqpvZCnpXauDukeOM9zxnUsxKcI9lTC66aLFtLuyq86vuPH8yCLara+KG3KC5It3K1LFX/WQa1DP6jRyE7RgGGt9LrJp6QT85AalpUmIiabg0bcdfihhdwlEpSQDKTuGG+8NY8DTreBPrKKBBhKOTJhaLP0KTU7pce9j31EKgEtMZ/df3sfzPtXVrpRTLTfdS/8yLUNKsZumlW1kMx/rfCDGRW4zy8Gu8yrNOM9Pry04xW0CzfTuoJJu4h+oZXfXrpUxiO1Y00EKPMwJkkDAJTr7xvCX+u2rPjCmGoWikh/zDTMf8BSK5zUjE=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        ConnecterUtil.connect(player,"SkyWarsLobby");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.LEGACY_EYE_OF_ENDER).build();
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

