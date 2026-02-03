package cn.starry.hub.menus.achievements.button;

import cn.starry.core.Core;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ChallengeAchievementButton extends Button {

    private final AbstractAchievement achievement;

    public ChallengeAchievementButton(AbstractAchievement achievement) {
        this.achievement = achievement;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();

        String name = achievement.getDisplayName();
        if (achievement.isHidden() && !AchievementManager.isUnlocked(player, achievement)) {
            name = "秘密成就";
        }
        String nameColor = AchievementManager.isUnlocked(player, achievement) ? "&a" : "&c";
        lores.add("   " + nameColor + name + "   ");
        lores.add(" ");

        List<String> description = achievement.getDescription();
        if (achievement.isHidden() && !AchievementManager.isUnlocked(player, achievement)) {
            lores.add("   &7???");
        } else {
            lores.addAll(formatDescription(description));
            lores.add(" ");
            lores.add("   &7奖励   ");
            lores.add("   &8+ &e" + achievement.getPoints() + " &7成就点数   ");
        }
        lores.add(" ");
        lores.add("   &7已被 &f" + Core.getInstance().getMongoDB().getPercentageOfAchievement(achievement.getInternalName()) + "% &7的玩家解锁   ");
        lores.add(" ");
        lores.add(AchievementManager.isUnlocked(player, achievement) ? "   &a成就已解锁   " : "   &c成就尚未解锁   ");
        lores.add(" ");

        ItemStack item;
        if (AchievementManager.isUnlocked(player, achievement)) {
            if (achievement.isPremium()) {
                item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).amount(achievement.getPoints()).shiny().build();
            } else {
                item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).amount(achievement.getPoints()).build();
            }
        } else {
            item = new ItemBuilder(Material.COAL).name(CC.translate(" ")).lore(lores).amount((achievement.isHidden() ? 1 : achievement.getPoints())).build();
        }
        
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

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

