package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.LuckPermsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AutoRegister
public class InteractiveMenu implements Listener {

    private Inventory inv;

    public void openMenu(Player player, Player target) {
        this.init(player,target);
        player.openInventory(this.inv);
    }

    public void init(Player player, Player target) {
        this.inv = Bukkit.createInventory(null, 54, target.getName());

        this.inv.setItem(0, new ItemBuilder(Material.SKULL_ITEM).durability(3).setNewSkullOwner(target).name(LuckPermsUtil.getPlayerRealColoredName(target.getUniqueId())).build());

        for (int i = 9; i < 18; i++) {
           this.inv.setItem(i,new ItemBuilder(Material.STAINED_GLASS_PANE).durability(0).build());
        }


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
        if (!e.getInventory().equals(this.inv)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getInventory().equals(this.inv)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
    }

}

