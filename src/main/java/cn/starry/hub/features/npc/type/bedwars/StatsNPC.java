package cn.starry.hub.features.npc.type.bedwars;

import cn.starry.core.Core;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.features.npc.AbstractNPC;
import dev.jnic.annotations.Include;
import me.clip.placeholderapi.PlaceholderAPI;
import com.bnstra.npclib.api.skin.Skin;
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
@Include
public class StatsNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "stats";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        int unlockedAchievements = 0;
        int totalAchievements = 0;
        for (AbstractAchievement achievements : Core.getInstance().getAchievementFactory().getAchievements()) {
            if (achievements.getType().equals(AchievementType.BEDWARS)) {
                totalAchievements++;
                if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                    unlockedAchievements++;
                }
            }
        }
        lines.add("&b&l你的起床战争信息");
        lines.add("&f你的等级 &b" + PlaceholderAPI.setPlaceholders(player,"%bedwars_level%"));
        //lines.add("&f进度 " + PlaceholderAPI.setPlaceholders(player,"&b%bw1058_player_xp_formatted%&7/&a%bw1058_player_rerq_xp_formatted%"));
        lines.add("&f成就 &e" + unlockedAchievements + "&8/&b" + totalAchievements);
        lines.add("&f总胜利数 " + PlaceholderAPI.setPlaceholders(player,"&b%bedwars_wins%"));
        lines.add("&8点击查看数据");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-9.5,14.0,23.5,180,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        /*
        Property property = ((CraftPlayer) player).getHandle().getProfile().getProperties().get("textures").iterator().next();
        String texture = property.getValue();
        String signature = property.getSignature();
        return new Skin(texture,signature);

         */
        return null;
    }

    @Override
    public void handlePlayerInteract(Player player) {
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.PAPER).build();
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

