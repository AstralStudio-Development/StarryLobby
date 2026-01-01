package cn.starry.hub.functions.menu.achievements;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;


public class ChallengePageMenu implements Listener {

    private Inventory inv;

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        this.inv = Bukkit.createInventory(null, 54, achievementType.getDisplayName() + "挑战成就");

        //Game Buttons
        List<AbstractAchievement> achievements = new ArrayList<>(Core.getInstance().getAchievementFactory().getAchievements());
        int slotIndex = 0;
        for (AbstractAchievement achievement : achievements) {
            if (achievement.getType().equals(achievementType) && slotIndex < this.getItemsSlots().length) {
                this.inv.setItem(this.getItemsSlots()[slotIndex], new AchievementsButtons().AchievementsButton(player, achievement));
                slotIndex++;
            }
        }

        this.inv.setItem(48, new AchievementsButtons().Back(achievementType));
        this.inv.setItem(49, new AchievementsButtons().TotalButton(player,achievementType,1));
        this.inv.setItem(50, new AchievementsButtons().toOtherPage(player));

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        if (achievementType == null) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (!e.getView().getTitle().equals(achievementType.getDisplayName() + "挑战成就")) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getView().getTitle().equals(achievementType.getDisplayName() + "挑战成就")) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().Back(achievementType))) {
            new AchievementsSubMenu().openMenu(player);
        }
        e.setCancelled(true);
    }

    public Integer[] getItemsSlots() {
        return new Integer[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
    }

}

