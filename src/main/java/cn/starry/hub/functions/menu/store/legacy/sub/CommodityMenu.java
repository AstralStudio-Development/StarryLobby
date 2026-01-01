package cn.starry.hub.functions.menu.store.legacy.sub;

import cn.starry.hub.functions.menu.buttons.CommodityButtons;
import cn.starry.hub.functions.menu.buttons.RankButtons;
import cn.starry.hub.functions.menu.buttons.StoreButtons;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.menu.store.legacy.StoreMenu;
import cn.starry.hub.utils.StoreUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@Deprecated
public class CommodityMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("             &0选择购买数量");

    public void openMenu(Player player,int type) {
        this.init(player,type);
        player.openInventory(this.inv);
    }

    public void init(Player player,int type) {
        this.inv = Bukkit.createInventory(null, 36, title);

        //BackButton
        //this.inv.setItem(31, new StoreButtons().Back());
        //Commodity
        if (type == 0) {
            //Points
            this.inv.setItem(11, new CommodityButtons().PointsItem(10, Material.GHAST_TEAR));
            this.inv.setItem(12, new CommodityButtons().PointsItem(60,Material.IRON_INGOT));
            this.inv.setItem(13, new CommodityButtons().PointsItem(180,Material.GOLD_INGOT));
            this.inv.setItem(14, new CommodityButtons().PointsItem(320,Material.DIAMOND));
            this.inv.setItem(15, new CommodityButtons().PointsItem(680,Material.EMERALD));
        } else if (type == 1) {
            //Rank
            this.inv.setItem(11, new RankButtons().VIP(player));
            this.inv.setItem(12, new RankButtons().VIPUpgrade(player));
            this.inv.setItem(13, new RankButtons().MVP(player));
            this.inv.setItem(14, new RankButtons().MVPUpgrade(player));
        } else if (type == 2) {
            //MysteryBox
            this.inv.setItem(11, new CommodityButtons().MysteryBoxItem(1,0,50));
            this.inv.setItem(12, new CommodityButtons().MysteryBoxItem(5,0,200));
            this.inv.setItem(13, new CommodityButtons().MysteryBoxItem(10,1,400));
            this.inv.setItem(14, new CommodityButtons().MysteryBoxItem(25,2,800));
            this.inv.setItem(15, new CommodityButtons().MysteryBoxItem(50,3,1500));
        } else if (type == 3) {
            //Horn
            this.inv.setItem(11, new CommodityButtons().HornItem(2,1));
            this.inv.setItem(12, new CommodityButtons().HornItem(10,5));
            this.inv.setItem(13, new CommodityButtons().HornItem(20,10));
            this.inv.setItem(14, new CommodityButtons().HornItem(30,15));
            this.inv.setItem(15, new CommodityButtons().HornItem(50,25));
        } else if (type == 4) {
            //Suffix
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
        if (!e.getView().getTitle().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getView().getTitle().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        String papi;
        if (StarryLobby.getInstance().economy) {
            papi = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");
        } else {
            papi = "0";
        }
        int points = Integer.parseInt(papi);

        //Back Button
        /*
        if (e.getCurrentItem().equals(new StoreButtons().Back())) {
            new StoreMenu().openMenu(player);
        }

         */
        //

        //Points
        if (e.getCurrentItem().equals(new CommodityButtons().PointsItem(10, Material.GHAST_TEAR))) {
            Bukkit.dispatchCommand(player,"mp point 1");
        }
        if (e.getCurrentItem().equals(new CommodityButtons().PointsItem(60,Material.IRON_INGOT))) {
            Bukkit.dispatchCommand(player,"mp point 6");
        }
        if (e.getCurrentItem().equals(new CommodityButtons().PointsItem(180,Material.GOLD_INGOT))) {
            Bukkit.dispatchCommand(player,"mp point 18");
        }
        if (e.getCurrentItem().equals(new CommodityButtons().PointsItem(320,Material.DIAMOND))) {
            Bukkit.dispatchCommand(player,"mp point 32");
        }
        if (e.getCurrentItem().equals(new CommodityButtons().PointsItem(680,Material.EMERALD))) {
            Bukkit.dispatchCommand(player,"mp point 68");
        }
        //

        //Rank
        if (e.getCurrentItem().equals(new RankButtons().VIP(player))) {
            if (!player.hasPermission("lobby.vip") && !player.hasPermission("group.vip")) {
                StoreUtil.buy(player, points, 400, "lp user %player_name% parent set vip", "", "", "向他人展示你的特权吧",true);
            } else {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
                player.sendMessage(CC.translate("&c已拥有此会员等级！"));
            }
        }
        if (e.getCurrentItem().equals(new RankButtons().VIPUpgrade(player))) {
            if (!player.hasPermission("lobby.vip+") && !player.hasPermission("group.vip+")) {
                StoreUtil.buy(player, points, 500, "lp user %player_name% parent set vip+", "", "", "向他人展示你的特权吧",true);
            } else {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
                player.sendMessage(CC.translate("&c已拥有此会员等级！"));
            }
        }
        if (e.getCurrentItem().equals(new RankButtons().MVP(player))) {
            if (!player.hasPermission("lobby.mvp") && !player.hasPermission("group.mvp")) {
                cn.starry.hub.utils.StoreUtil.buy(player, points,1000,"lp user %player_name% parent set mvp","","","向他人展示你的特权吧",true);
            } else {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
                player.sendMessage(CC.translate("&c已拥有此会员等级！"));
            }
        }
        if (e.getCurrentItem().equals(new RankButtons().MVPUpgrade(player))) {
            if (!player.hasPermission("lobby.mvp+") && !player.hasPermission("group.mvp+")) {
                StoreUtil.buy(player, points,1500,"lp user %player_name% parent set mvp+","","","向他人展示你的特权吧",true);
            } else {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
                player.sendMessage(CC.translate("&c已拥有此会员等级！"));
            }
        }
        //

        //MysteryBox
        if (e.getCurrentItem().equals(new CommodityButtons().MysteryBoxItem(1,0,50))) {
            StoreUtil.buy(player, points,50,"gmysteryboxes give %player_name% 1","","","&f打开宝藏吧",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().MysteryBoxItem(5,0,200))) {
            StoreUtil.buy(player, points,200,"gmysteryboxes give %player_name% 5","","","&f打开宝藏吧",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().MysteryBoxItem(10,1,400))) {
            StoreUtil.buy(player, points,400,"gmysteryboxes give %player_name% 8","gmysteryboxes give %player_name% 2 5","","&f打开宝藏吧",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().MysteryBoxItem(25,2,800))) {
            StoreUtil.buy(player, points,800,"gmysteryboxes give %player_name% 19","gmysteryboxes give %player_name% 3 4","gmysteryboxes give %player_name% 3 5","&f打开宝藏吧",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().MysteryBoxItem(50,3,1500))) {
            StoreUtil.buy(player, points,1500,"gmysteryboxes give %player_name% 40","gmysteryboxes give %player_name% 5 4","gmysteryboxes give %player_name% 5 5","&f打开宝藏吧",false);
        }
        //

        //Horn
        if (e.getCurrentItem().equals(new CommodityButtons().HornItem(2,1))) {
            StoreUtil.buy(player, points,1,"lbadmin give %player_name% 2","","","&f世人将会听到你的声音",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().HornItem(10,5))) {
            StoreUtil.buy(player, points,5,"lbadmin give %player_name% 10","","","&f世人将会听到你的声音",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().HornItem(20,10))) {
            StoreUtil.buy(player, points,10,"lbadmin give %player_name% 20","","","&f世人将会听到你的声音",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().HornItem(30,15))) {
            StoreUtil.buy(player, points,15,"lbadmin give %player_name% 30","","","&f世人将会听到你的声音",false);
        }
        if (e.getCurrentItem().equals(new CommodityButtons().HornItem(50,25))) {
            StoreUtil.buy(player, points,25,"lbadmin give %player_name% 50","","","&f世人将会听到你的声音",false);
        }
        //

    }

}

