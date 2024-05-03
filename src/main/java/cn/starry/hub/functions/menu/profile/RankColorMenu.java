package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.buttons.RankColorButtons;
import cn.starry.hub.functions.rank.RankColors;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class RankColorMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("自定义颜色");

    public void openMenu(Player player,boolean isByCommand) {
        this.init(player,isByCommand);
        player.openInventory(this.inv);
    }

    public void init(Player player,boolean isByCommand) {
        this.inv = Bukkit.createInventory(null, 45, title);

        this.inv.setItem(10, new RankColorButtons().Red_ColorButton(player));
        this.inv.setItem(11, new RankColorButtons().Gold_ColorButton(player));
        this.inv.setItem(12, new RankColorButtons().Green_ColorButton(player));
        this.inv.setItem(13, new RankColorButtons().Yellow_ColorButton(player));
        this.inv.setItem(14, new RankColorButtons().Pink_ColorButton(player));
        this.inv.setItem(15, new RankColorButtons().White_ColorButton(player));
        this.inv.setItem(16, new RankColorButtons().Blue_ColorButton(player));
        this.inv.setItem(19, new RankColorButtons().Dark_Green_ColorButton(player));
        this.inv.setItem(20, new RankColorButtons().Dark_Red_ColorButton(player));
        this.inv.setItem(21, new RankColorButtons().Cyan_ColorButton(player));
        this.inv.setItem(22, new RankColorButtons().Purple_ColorButton(player));
        this.inv.setItem(23, new RankColorButtons().Gray_ColorButton(player));
        this.inv.setItem(24, new RankColorButtons().Black_ColorButton(player));
        this.inv.setItem(25, new RankColorButtons().Dark_Blue_ColorButton(player));

        if (isByCommand) {
            this.inv.setItem(40, new RankColorButtons().Close());
        } else {
            this.inv.setItem(40, new RankColorButtons().Back());
        }

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
        if (e.getCurrentItem().equals(new RankColorButtons().Close())) {
            player.closeInventory();
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Back())) {
            new CustomViewMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Red_ColorButton(player))) {
            RankColors rankColors = RankColors.RED;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Gold_ColorButton(player))) {
            RankColors rankColors = RankColors.GOLD;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Green_ColorButton(player))) {
            RankColors rankColors = RankColors.GREEN;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Yellow_ColorButton(player))) {
            RankColors rankColors = RankColors.YELLOW;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Pink_ColorButton(player))) {
            RankColors rankColors = RankColors.PINK;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().White_ColorButton(player))) {
            RankColors rankColors = RankColors.WHITE;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Blue_ColorButton(player))) {
            RankColors rankColors = RankColors.BLUE;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Dark_Green_ColorButton(player))) {
            RankColors rankColors = RankColors.DARK_GREEN;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Dark_Red_ColorButton(player))) {
            RankColors rankColors = RankColors.DARK_RED;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Cyan_ColorButton(player))) {
            RankColors rankColors = RankColors.CYAN;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Purple_ColorButton(player))) {
            RankColors rankColors = RankColors.PURPURE;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Black_ColorButton(player))) {
            RankColors rankColors = RankColors.BLACK;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Dark_Blue_ColorButton(player))) {
            RankColors rankColors = RankColors.DARK_BLUE;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
        if (e.getCurrentItem().equals(new RankColorButtons().Gray_ColorButton(player))) {
            RankColors rankColors = RankColors.GRAY;
            player.closeInventory();
            if (!Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
                Main.getInstance().getData().updatePlayerData(uuid,"rankColor",rankColors.toString());
                player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                player.sendMessage(ColorUtil.color("&a已更新你的Rank颜色！"));
            } else {
                player.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT,1,1);
                player.sendMessage(ColorUtil.color("&c已选择此项！"));
            }
        }
    }

}

