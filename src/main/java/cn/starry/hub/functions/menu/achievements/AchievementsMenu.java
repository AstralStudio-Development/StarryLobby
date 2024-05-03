package cn.starry.hub.functions.menu.achievements;

import cn.starry.hub.api.data.CacheData;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class AchievementsMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                  &0成就");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        //Achievements Buttons
        this.inv.setItem(1, new AchievementsButtons().TotalButton(player, AchievementType.GENERAL,3));
        this.inv.setItem(30, new AchievementsButtons().TotalButton(player,AchievementType.BEDWARS,3));
        this.inv.setItem(34, new AchievementsButtons().TotalButton(player,AchievementType.PIT,3));

        this.inv.setItem(48, new AchievementsButtons().BackToProfile());
        this.inv.setItem(49, new AchievementsButtons().TotalButton(player,null,4));
        this.inv.setItem(50, new AchievementsButtons().AchievementRewards(player));

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (!e.getInventory().getName().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getInventory().getName().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().BackToProfile())) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.GENERAL,3))) {
            CacheData.ACHIEVEMENT.put(player,AchievementType.GENERAL);
            new AchievementsSubMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.BEDWARS,3))) {
            CacheData.ACHIEVEMENT.put(player,AchievementType.BEDWARS);
            new AchievementsSubMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.PIT,3))) {
            CacheData.ACHIEVEMENT.put(player,AchievementType.PIT);
            new AchievementsSubMenu().openMenu(player);
        }
    }

}

