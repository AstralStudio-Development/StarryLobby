package cn.starry.hub.functions.npc.type.bedwars;

import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.Main;
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

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
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
        for (AbstractAchievement achievements : Main.getInstance().getAchievementFactory().getAchievements()) {
            if (achievements.getType().equals(AchievementType.BEDWARS)) {
                totalAchievements++;
                if (Main.getInstance().getData().getAchievementData(player.getUniqueId(), "achievements").contains(achievements.getInternalName())) {
                    unlockedAchievements++;
                }
            }
        }
        lines.add("&6&l你的起床战争信息");
        lines.add("&f你的等级: " + PlaceholderAPI.setPlaceholders(player,"%bw1058_player_level%"));
        lines.add("&f进度: " + PlaceholderAPI.setPlaceholders(player,"&b%bw1058_player_xp_formatted%&7/&a%bw1058_player_rerq_xp_formatted%"));
        lines.add("&f成就: &e" + unlockedAchievements + "&a/" + totalAchievements);
        lines.add("&f总胜利数: " + PlaceholderAPI.setPlaceholders(player,"&a%bw1058_stats_wins%"));
        lines.add("&e&l点击查看数据");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),2.0,67.0,-17.0,65,0);
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
