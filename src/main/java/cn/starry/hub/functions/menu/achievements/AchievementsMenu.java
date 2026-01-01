package cn.starry.hub.functions.menu.achievements;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.utils.chat.CC;
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

    String title = CC.translate("                  &0成就");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 36, title);

        //Achievements Buttons
        this.inv.setItem(1, new AchievementsButtons().TotalButton(player, AchievementType.GENERAL,3));
        this.inv.setItem(10, new AchievementsButtons().TotalButton(player,AchievementType.BEDWARS,3));
        this.inv.setItem(11, new AchievementsButtons().TotalButton(player,AchievementType.PIT,3));
        this.inv.setItem(12, new AchievementsButtons().TotalButton(player,AchievementType.SKYWARS,3));
        this.inv.setItem(13, new AchievementsButtons().TotalButton(player,AchievementType.DUELS,3));

        this.inv.setItem(30, new AchievementsButtons().BackToProfile());
        this.inv.setItem(31, new AchievementsButtons().TotalButton(player,null,4));
        //this.inv.setItem(32, new AchievementsButtons().AchievementRewards(player));

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
        if (!e.getView().getTitle().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getView().getTitle().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,null,4))) {
            e.setCancelled(true);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().BackToProfile())) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.GENERAL,3))) {
            CacheData.ACHIEVEMENT_MENU.put(player,AchievementType.GENERAL);
            new AchievementsSubMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.BEDWARS,3))) {
            CacheData.ACHIEVEMENT_MENU.put(player,AchievementType.BEDWARS);
            new AchievementsSubMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.PIT,3))) {
            CacheData.ACHIEVEMENT_MENU.put(player,AchievementType.PIT);
            new AchievementsSubMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.SKYWARS,3))) {
            CacheData.ACHIEVEMENT_MENU.put(player,AchievementType.SKYWARS);
            new AchievementsSubMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new AchievementsButtons().TotalButton(player,AchievementType.DUELS,3))) {
            CacheData.ACHIEVEMENT_MENU.put(player,AchievementType.DUELS);
            new AchievementsSubMenu().openMenu(player);
        }
        e.setCancelled(true);
    }

}

