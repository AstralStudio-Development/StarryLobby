package cn.starry.hub.functions.achievement;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer, duduskz
 * @Date: 2023/12/19
 */
public class AchievementManager {

    public static void unlockAchievement(Player player, AbstractAchievement achievement) {
        UUID uuid = player.getUniqueId();
        if (achievement == null) {
            player.sendMessage(ColorUtil.color("&c不存在此成就,请联系管理员修复！"));
            return;
        }
        if (!Main.getInstance().getData().getAchievementData(uuid,"achievements").contains(achievement.getInternalName())) {
            StringBuilder stringBuilder1 = new StringBuilder("§e§kA§a>>  ");
            String message11 = "§a已解锁成就: ";
            stringBuilder1.append(message11);
            TextComponent tc = new TextComponent(stringBuilder1.toString());
            TextComponent tc1 = new TextComponent(achievement.getDisplayName());
            tc1.setColor(ChatColor.GOLD);
            StringBuilder lore = new StringBuilder("§a" + achievement.getDisplayName() + "\n");
            List<String> description = achievement.getDescription();
            for (String line : description) {
                lore.append(ColorUtil.color(line)).append("\n");
            }
            lore.append("\n§7奖励:\n§8 +§e").append(achievement.getPoints()).append("点§7成就点数");
            //String lore = "§a" + achievement.getDisplayName() + "\n§f" + achievement.getDescription() + "\n\n§7奖励\n§8+§e" + achievement.getPoints() + "§7成就点数\n\n§e点击打开Fairylands成就！";
            tc1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(lore.toString()).create()));
            //tc1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND);
            StringBuilder message1 = new StringBuilder("  §a<<§e§kA");
            TextComponent tc2 = new TextComponent(message1.toString());
            tc.addExtra(tc1);
            tc.addExtra(tc2);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 24.0f);
            player.spigot().sendMessage(tc);
            Main.getInstance().getData().updateAchievementData(uuid,"achievements",achievement.getInternalName());
            Main.getInstance().getData().updateAchievementPoints(uuid,"points",achievement.getPoints());
        }
    }

    public static void unlockAchievement(UUID uuid, AbstractAchievement achievement) {
        if (achievement == null) {
            Bukkit.getPlayer(uuid).sendMessage(ColorUtil.color("&c不存在此成就,请联系管理员修复！"));
            return;
        }
        if (!Main.getInstance().getData().getAchievementData(uuid,"achievements").contains(achievement.getInternalName())) {
            StringBuilder stringBuilder1 = new StringBuilder("§e§kA§a>>  ");
            String message11 = "§a已解锁成就: ";
            stringBuilder1.append(message11);
            TextComponent tc = new TextComponent(stringBuilder1.toString());
            TextComponent tc1 = new TextComponent(achievement.getDisplayName());
            tc1.setColor(ChatColor.GOLD);
            StringBuilder lore = new StringBuilder("§a" + achievement.getDisplayName() + "\n");
            List<String> description = achievement.getDescription();
            for (String line : description) {
                lore.append(ColorUtil.color(line)).append("\n");
            }
            lore.append("\n§7奖励:\n§8 +§e").append(achievement.getPoints()).append("点§7成就点数");
            //String lore = "§a" + achievement.getDisplayName() + "\n§f" + achievement.getDescription() + "\n\n§7奖励\n§8+§e" + achievement.getPoints() + "§7成就点数\n\n§e点击打开Fairylands成就！";
            tc1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(lore.toString()).create()));
            //tc1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND);
            StringBuilder message1 = new StringBuilder("  §a<<§e§kA");
            TextComponent tc2 = new TextComponent(message1.toString());
            tc.addExtra(tc1);
            tc.addExtra(tc2);
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 24.0f);
            Bukkit.getPlayer(uuid).spigot().sendMessage(tc);
            Main.getInstance().getData().updateAchievementData(uuid,"achievements",achievement.getInternalName());
            Main.getInstance().getData().updateAchievementPoints(uuid,"points",achievement.getPoints());
        }
    }

    public static boolean isUnlocked(Player player,AbstractAchievement achievement) {
        UUID uuid = player.getUniqueId();
        return Main.getInstance().getData().getAchievementData(uuid, "achievements").contains(achievement.getInternalName());
    }

    public static AbstractAchievement getAchievement(String name) {
        for (AbstractAchievement achievement : Main.getInstance().getAchievementFactory().getAchievements()) {
            if (achievement.getInternalName().equalsIgnoreCase(name)) {
                return achievement;
            }
        }
        return null;
    }

}
