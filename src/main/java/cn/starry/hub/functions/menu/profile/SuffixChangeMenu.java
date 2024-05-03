package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.LuckPermsUtil;
import cn.starry.hub.functions.menu.buttons.SuffixButtons;
import cn.starry.hub.functions.menu.store.StoreMenu;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@AutoRegister
public class SuffixChangeMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                &0后缀更改");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 36, title);
        UUID uuid = player.getUniqueId();

        //Suffix Item
        this.inv.setItem(10, new SuffixButtons().None(player));
        this.inv.setItem(11, new SuffixButtons().Flowers(player,false));
        this.inv.setItem(12, new SuffixButtons().LittleFlowers(player,false));
        this.inv.setItem(13, new SuffixButtons().Star(player,false));
        this.inv.setItem(14, new SuffixButtons().Fairylands(player,false));
        this.inv.setItem(15, new SuffixButtons().ZZSF(player,false));
        this.inv.setItem(16, new SuffixButtons().Guild(player));

        this.inv.setItem(31, new SuffixButtons().Back());

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
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
        //

        //无
        if (e.getCurrentItem().equals(new SuffixButtons().None(player))) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (suffix.equals("")) {
                player.closeInventory();
                player.sendMessage(ColorUtil.color("&c你已经选择了这个后缀"));
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,0);
            } else {
                player.closeInventory();
                player.sendMessage(ColorUtil.color("&f你选择了 &a无"));
                player.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,2);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, "lp user %player_name% meta setsuffix \"\" "));
            }
        }
        //花
        if (e.getCurrentItem().equals(new SuffixButtons().Flowers(player,false))) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.follow") || player.hasPermission("suffix.flower")) {
                if (suffix.equals(" &e&l❀")) {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&c你已经选择了这个后缀"));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 0);
                } else {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&f你选择了 &a花"));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, "lp user %player_name% meta setsuffix \" &e&l❀\" "));
                }
            } else {
                new StoreMenu().openMenu(player);
            }
        }
        //小鲜花
        if (e.getCurrentItem().equals(new SuffixButtons().LittleFlowers(player,false))) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.xxh") || player.hasPermission("suffix.xxh")) {
                if (suffix.equals(" &8[&c小鲜花&8]")) {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&c你已经选择了这个后缀"));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 0);
                } else {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&f你选择了 &a小鲜花"));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, "lp user %player_name% meta setsuffix \" &8[&c小鲜花&8]\" "));
                }
            } else {
                new StoreMenu().openMenu(player);
            }
        }
        //星星
        if (e.getCurrentItem().equals(new SuffixButtons().Star(player,false))) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.star") || player.hasPermission("suffix.star")) {
                if (suffix.equals(" &b&l✰")) {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&c你已经选择了这个后缀"));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 0);
                } else {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&f你选择了 &a星星"));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, "lp user %player_name% meta setsuffix \" &b&l✰\" "));
                }
            } else {
                new StoreMenu().openMenu(player);
            }
        }
        //仙境
        if (e.getCurrentItem().equals(new SuffixButtons().Fairylands(player,false))) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.fairylands") || player.hasPermission("suffix.fairylands")) {
                if (suffix.equals(" &8[&f仙境&8]")) {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&c你已经选择了这个后缀"));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 0);
                } else {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&f你选择了 &a仙境"));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, "lp user %player_name% meta setsuffix \" &8[&f仙境&8]\" "));
                }
            } else {
                new StoreMenu().openMenu(player);
            }
        }
        //征战四方
        if (e.getCurrentItem().equals(new SuffixButtons().ZZSF(player,false))) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.zzsf") || player.hasPermission("suffix.zzsf")) {
                if (suffix.equals(" &8[&b征&c战&d四&e方&8]&a✔")) {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&c你已经选择了这个后缀"));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 0);
                } else {
                    player.closeInventory();
                    player.sendMessage(ColorUtil.color("&f你选择了 &a征战四方"));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, "lp user %player_name% meta setsuffix \" &8[&b征&c战&d四&e方&8]&a✔\" "));
                }
            } else {
                new StoreMenu().openMenu(player);
            }
        }

        //
        if (e.getCurrentItem().equals(new SuffixButtons().Back())) {
            new PlayerProfileMenu().openMenu(player);
        }
    }

}

