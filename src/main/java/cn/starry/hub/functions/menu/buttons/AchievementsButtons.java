package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.LuckPermsUtil;
import cn.starry.core.utils.RankUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AchievementsButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack ChallengeButton(Player player, AchievementType achievementType) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        int unlockedAchievements = 0;
        int totalAchievements = 0;
        int availablePoints = 0;
        int totalPoints = 0;
        for (AbstractAchievement achievements : Core.getInstance().getAchievementFactory().getAchievements()) {
            if (achievements.getType().equals(achievementType)) {
                totalAchievements++;
                totalPoints = totalPoints + achievements.getPoints();
                if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                    unlockedAchievements++;
                    availablePoints = availablePoints + achievements.getPoints();
                }
            }
        }
        lores.add("   &a挑战成就   ");
        lores.add("   &8" + achievementType.getDisplayName() + "   ");
        lores.add(" ");
        lores.add("   &7已解锁 &b" + unlockedAchievements + "&7/&b" + totalAchievements + " &8(" + numberFormat.format((float) unlockedAchievements / (float) totalAchievements * 100) + "%)   ");
        lores.add("   &7点数 &e" + availablePoints + "&7/&e" + totalPoints + " &8(" + numberFormat.format((float) availablePoints / (float) totalPoints * 100) + "%)   ");
        lores.add(" ");
        lores.add("   &7挑战成就可一次性完成   ");
        lores.add(" ");
        lores.add("   &a+ &f点击查看成就   ");
        lores.add(" ");

        item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack GradeButton(Player player,AchievementType achievementType) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a分级成就   ");
        lores.add("   &8" + achievementType.getDisplayName() + "   ");
        lores.add(" ");
        lores.add("   &7已解锁 &bN&7/&bA   ");
        lores.add("   &7点数 &eN&7/&eA   ");
        lores.add(" ");
        lores.add("   &7分级成就需要   ");
        lores.add("   &7完成多个级别的成就   ");
        lores.add(" ");
        lores.add("   &c此分类即将开放   ");
        lores.add(" ");

        item = new ItemBuilder(Material.DIAMOND_BLOCK).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TotalButton(Player player,AchievementType achievementType,int id) {
        UUID uuid = player.getUniqueId();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        int unlockedAchievements = 0;
        int totalAchievements = 0;
        int availablePoints = 0;
        int totalPoints = 0;
        for (AbstractAchievement achievements : Core.getInstance().getAchievementFactory().getAchievements()) {
            if (id == 0 || id == 1 || id == 2 || id == 3 && achievementType != null) {
                if (achievements.getType().equals(achievementType)) {
                    totalAchievements++;
                    totalPoints = totalPoints + achievements.getPoints();
                    if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                        unlockedAchievements++;
                        availablePoints = availablePoints + achievements.getPoints();
                    }
                }
            } else {
                totalAchievements++;
                totalPoints = totalPoints + achievements.getPoints();
                if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                    unlockedAchievements++;
                    availablePoints = availablePoints + achievements.getPoints();
                }
            }
        }
        List<String> lores = new ArrayList<>();
        if (id == 0) {
            lores.add("   &a总完成度   ");
        } else if (id == 1) {
            lores.add("   &a挑战成就   ");
        } else if (id == 2) {
            lores.add("   &a分级成就   ");
        }

        if (id == 0 || id == 1 || id == 2 && achievementType != null) {
            lores.add("   &8" + achievementType.getDisplayName() + "   ");
            lores.add(" ");
        } else {
            if (id != 3) {
                lores.add("   &a成就完成进度   ");
                lores.add(" ");
                //lores.add("   &7玩家 " + RankUtil.getFormatRankById(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rank"),player.getUniqueId()) + player.getDisplayName() + "   ");
            } else {
                lores.add("   &a" + achievementType.getDisplayName() + "成就   ");
                lores.add(" ");
            }
        }
        lores.add("   &7已解锁 &b" + unlockedAchievements + "&7/&b" + totalAchievements + " &8(" + numberFormat.format((float) unlockedAchievements / (float) totalAchievements * 100) + "%)   ");
        lores.add("   &7点数 &e" + availablePoints + "&7/&e" + totalPoints + " &8(" + numberFormat.format((float) availablePoints / (float) totalPoints * 100) + "%)   ");
        lores.add(" ");
        if (id == 3 && achievementType != null) {
            lores.add("   &a+ &f点击查看成就   ");
            lores.add(" ");
        }
        if (id == 0) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
            meta = item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            return item;
        } else if (id == 1) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
            meta = item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            return item;
        } else if (id == 2) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
            meta = item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            return item;
        } else if (id == 3) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
            meta = item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            return item;
        } else {
            item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).build();
            meta = item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            return item;
        }
    }

    public ItemStack Back(AchievementType achievementType) {
        List<String> lores = new ArrayList<>();
        if (achievementType != null) {
            lores.add("   &a返回   ");
            lores.add(" ");
            lores.add("   &7返回至" + achievementType.getDisplayName() + "   ");
            lores.add(" ");
        } else {
            lores.add("   &a返回   ");
            lores.add(" ");
            lores.add("   &7返回至成就   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.ARROW).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack BackToProfile() {
        List<String> lores = new ArrayList<>();
        lores.add("   &a返回   ");
        lores.add(" ");
        lores.add("   &7返回至个人档案   ");
        lores.add(" ");
        item = new ItemBuilder(Material.ARROW).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack AchievementsButton(Player player, AbstractAchievement achievement) {
        List<String> lores = new ArrayList<>();

        String name = achievement.getDisplayName();
        if (achievement.isHidden() && !AchievementManager.isUnlocked(player, achievement)) {
            name = "秘密成就";
        }
        String nameColor = AchievementManager.isUnlocked(player, achievement) ? "&a" : "&c";
        lores.add("   " + nameColor + name + "   ");
        lores.add(" ");

        List<String> description = achievement.getDescription();
        if (achievement.isHidden() && !AchievementManager.isUnlocked(player,achievement)) {
            lores.add("   &7？？？");
        } else {
            lores.addAll(formatDescription(description));
            lores.add(" ");
            lores.add("   &7奖励   ");
            lores.add("   &8+ &e" + achievement.getPoints() + " &7成就点数   ");
        }
        lores.add(" ");
        lores.add("   &7已被 &f" + Core.getInstance().getMongoDB().getPercentageOfAchievement(achievement.getInternalName()) + "% &7的玩家解锁   ");
        lores.add(" ");
        lores.add(AchievementManager.isUnlocked(player,achievement) ? "   &a成就已解锁   " : "   &c成就尚未解锁   ");
        lores.add(" ");

        if (AchievementManager.isUnlocked(player,achievement)) {
            if (achievement.isPremium()) {
                item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).amount(achievement.getPoints()).shiny().build();
            } else {
                item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).amount(achievement.getPoints()).build();
            }
            meta = item.getItemMeta();
            item.setItemMeta(meta);
            return item;
        } else {
            item = new ItemBuilder(Material.COAL).name(CC.translate(" ")).lore(lores).amount((achievement.isHidden() ? 1 : achievement.getPoints())).build();
            meta = item.getItemMeta();
            item.setItemMeta(meta);
            return item;
        }
    }

    public ItemStack AchievementRewards(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7解锁成就达人专属奖励");
        lores.add("&c此功能仍在开发,将于未来一段时间内上线");
        item = new ItemBuilder(Material.GOLD_INGOT).name(CC.translate("&6成就奖励")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack toOtherPage(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a前往分级成就   ");
        lores.add(" ");
        lores.add("   &7点击查看综合分级成就   ");
        lores.add("   &c此功能仍在开发,将于未来一段时间内上线   ");
        lores.add(" ");
        item = new ItemBuilder(Material.DIAMOND_BLOCK).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    private List<String> formatDescription(List<String> description) {
        List<String> formatted = new ArrayList<>();
        for (String line : description) {
            StringBuilder currentLine = new StringBuilder();
            double currentWidth = 0;
            String lastColor = "&7";

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                
                if (c == '&' && i + 1 < line.length()) {
                    char next = line.charAt(i + 1);
                    if ("0123456789abcdefklmnor".indexOf(Character.toLowerCase(next)) != -1) {
                        currentLine.append(c).append(next);
                        lastColor = "&" + next;
                        i++;
                        continue;
                    }
                }

                double charWidth = (c > 128) ? 2 : 1;
                
                if (currentWidth + charWidth > 24) {
                    formatted.add("   " + currentLine.toString() + "   ");
                    currentLine = new StringBuilder();
                    currentLine.append(lastColor);
                    currentWidth = 0;
                }
                
                currentLine.append(c);
                currentWidth += charWidth;
            }
            if (currentLine.length() > 0) {
                formatted.add("   " + currentLine.toString() + "   ");
            }
        }
        return formatted;
    }

}
