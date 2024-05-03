package cn.starry.hub.functions.menu.activity;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.ActivityButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.UUID;

@AutoRegister
public class ActivityGuideMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                &0活动指南");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 36, title);
        UUID uuid = player.getUniqueId();

        this.inv.setItem(13, new ActivityButtons().LinkItem(player));

        this.inv.setItem(31, new ActivityButtons().Close());

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
        if (e.getCurrentItem().equals(new ActivityButtons().LinkItem(player))) {
            BookUtil.openPlayer(player,
                     BookUtil.writtenBook()
                             .title("")
                             .author("")
                             .pages(
                                    new BookUtil.PageBuilder()
                                            .add(
                                                    ColorUtil.color("&8不知道活动具体流程?")
                                            )
                                            .newLine()
                                            .add(
                                                    ColorUtil.color("&8点击以下链接查看详情！")
                                            )
                                            .newLine()
                                            .newLine()
                                            .add(
                                                    BookUtil.TextBuilder
                                                            .of(ColorUtil.color("     &b&l&n点我前往！"))
                                                            .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&b看看他的")))
                                                            .onClick(BookUtil.ClickAction.openUrl("https://space.bilibili.com/447409448?spm_id_from=333.337.0.0"))
                                                            .build()
                                            )
                                            .build()
                            )
                            .build()
            );
        }
        if (e.getCurrentItem().equals(new ActivityButtons().Close())) {
            player.closeInventory();
        }
    }

}

