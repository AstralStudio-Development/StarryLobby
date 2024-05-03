package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.AccountStatusButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AutoRegister
public class AccountStatusMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("               &0账号状态");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 27, title);

        this.inv.setItem(0, new AccountStatusButtons().CurrentBanLevelButton(player));
        this.inv.setItem(1, new AccountStatusButtons().CompetitiveBanStatusButton(player));

        for (int i = 9; i < 18; i++) {
           this.inv.setItem(i,new AccountStatusButtons().GlassButton(player));
        }

        this.inv.setItem(22, new AccountStatusButtons().BackToProfile());

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
        if (e.getCurrentItem().equals(new AccountStatusButtons().BackToProfile())) {
            new PlayerProfileMenu().openMenu(player);
        }
    }

}

