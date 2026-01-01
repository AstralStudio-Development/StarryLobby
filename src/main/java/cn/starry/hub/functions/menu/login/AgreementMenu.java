package cn.starry.hub.functions.menu.login;

import cn.starry.hub.functions.menu.buttons.AgreementButtons;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.ConnecterUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class AgreementMenu implements Listener {

    private Inventory inv;


    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 45, "玩家协议");

        this.inv.setItem(13, new AgreementButtons().AgreementButton(player));

        this.inv.setItem(29, new AgreementButtons().AgreeButton(player));

        this.inv.setItem(33, new AgreementButtons().DisAgreeButton(player));

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
        if (!e.getView().getTitle().equalsIgnoreCase("玩家协议")) {
            return;
        }
        if (e.getCurrentItem().equals(new AgreementButtons().AgreementButton(player))) {
            player.sendMessage(CC.translate("&b↓ 点击打开玩家协议 ↓"));
            player.sendMessage(CC.translate("&7\"很明显，他们已经准备好了。\""));
            player.sendMessage(CC.translate("&b&nhttps://www.yume.games/eula/"));
            player.closeInventory();
            e.setCancelled(true);
        }
        if (e.getCurrentItem().equals(new AgreementButtons().AgreeButton(player))) {
            ConnecterUtil.connect(player,"MainLobby");
            e.setCancelled(true);
        }
        if (e.getCurrentItem().equals(new AgreementButtons().DisAgreeButton(player))) {
            ((Player) e.getWhoClicked()).kickPlayer(ChatColor.RED + "你被踢出了服务器" + "\n" + "" + "\n" + ChatColor.GRAY + "原因: 不同意玩家协议" + "\n" + "" + "\n" + "当您想要进入服务器时,必须同意我们的玩家协议"+ "\n" + "其目的在于保障我们与玩家的权益");
            e.setCancelled(true);
        }
    }

}
