package cn.starry.hub.functions.menu.achievements;

import cn.starry.hub.api.data.CacheData;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class AchievementsSubMenu implements Listener {

    private Inventory inv;

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT.get(player);
        this.inv = Bukkit.createInventory(null, 36, ColorUtil.color(achievementType.getDisplayName() + "成就"));

        //Achievements Buttons
        this.inv.setItem(11, new AchievementsButtons().ChallengeButton(player, achievementType));
        this.inv.setItem(15, new AchievementsButtons().GradeButton(player,achievementType));

        this.inv.setItem(30, new AchievementsButtons().Back(null));
        this.inv.setItem(31, new AchievementsButtons().TotalButton(player,achievementType,0));

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        AchievementType achievementType = CacheData.ACHIEVEMENT.get(player);
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
        if (!e.getInventory().getName().equals(ColorUtil.color(achievementType.getDisplayName() + "成就"))) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getInventory().getName().equals(ColorUtil.color(achievementType.getDisplayName() + "成就"))) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().Back(null))) {
            CacheData.ACHIEVEMENT.remove(player);
            new AchievementsMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().ChallengeButton(player,achievementType))) {
            new ChallengePageMenu().openMenu(player);
        }
    }

}

