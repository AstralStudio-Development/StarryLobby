package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.buttons.DeliveryButtons;
import cn.starry.hub.functions.npc.type.lobby.DeliveryNPC;
import com.alonsoaliaga.alonsolevels.api.AlonsoLevelsAPI;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import com.yapzhenyie.GadgetsMenu.utils.mysteryboxes.MysteryBoxType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DeliveryMenu implements Listener {

    static HashMap<Player, BukkitTask> task = new HashMap<>();

    private Inventory inv;

    String title = ColorUtil.color("                &0礼包使者");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        UUID uuid = player.getUniqueId();
        this.inv = Bukkit.createInventory(null, 45, title);
        task.remove(player);
        BukkitTask refresh = new BukkitRunnable() {
            int i = 0;
            public void run() {
                if (this.i == 1) {
                    this.i = 0;
                }
                if (player.getInventory() != inv) {
                    cancel();
                }
                inv.setItem(4, new DeliveryButtons().PublicizeVideoButton(player));
                inv.setItem(20, Main.getInstance().getData().getDeliveryData(uuid,"default") ? new DeliveryButtons().getDoneBoxItem(player) : new DeliveryButtons().getDefaultItem(player));
                inv.setItem(21, Main.getInstance().getData().getDeliveryData(uuid,"vip") ? new DeliveryButtons().getDoneBoxItem(player) : new DeliveryButtons().getVIPItem(player));
                inv.setItem(22, Main.getInstance().getData().getDeliveryData(uuid,"vipPlus") ? new DeliveryButtons().getDoneBoxItem(player) : new DeliveryButtons().getVIPPlusItem(player));
                inv.setItem(23, Main.getInstance().getData().getDeliveryData(uuid,"mvp") ? new DeliveryButtons().getDoneBoxItem(player) : new DeliveryButtons().getMVPItem(player));
                inv.setItem(24, Main.getInstance().getData().getDeliveryData(uuid,"mvpPlus") ? new DeliveryButtons().getDoneBoxItem(player) : new DeliveryButtons().getMVPPlusItem(player));
                //inv.setItem(32, Main.getInstance().getData().getDeliveryData(uuid,"daily") ? new DeliveryButtons().getDoneDailyItem(player) : new DeliveryButtons().getDailyItem(player));
                ++i;
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0L, 20L);
        task.put(player, refresh);
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
        PlayerManager GadgetsAPI = GadgetsMenuAPI.getPlayerManager(player);
        if (e.getCurrentItem().equals(new DeliveryButtons().getDefaultItem(player))) {
            boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"default");
            if (!isReceive) {
                Main.getInstance().getData().updateDeliveryData(uuid,"default",true);
                player.sendMessage(ColorUtil.color("§a你领取了这个奖励！"));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                this.init(player);
                GadgetsAPI.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_1, System.currentTimeMillis() + 86400000L, true, null, 5);
                playDeliveryEffects(player);
            }
        }
        if (e.getCurrentItem().equals(new DeliveryButtons().getVIPItem(player))) {
            boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"vip");
            if (!isReceive) {
                if (player.hasPermission("group.vip")) {
                    Main.getInstance().getData().updateDeliveryData(uuid, "vip", true);
                    player.sendMessage(ColorUtil.color("§a你领取了这个奖励！"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.init(player);
                    GadgetsAPI.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_2, System.currentTimeMillis() + 86400000L, true, null, 5);
                    playDeliveryEffects(player);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    player.sendMessage(ColorUtil.color("&c你的会员等级不足以领取该奖励！请在&b货摊&c购买会员"));
                    player.closeInventory();
                }
            }
        }
        if (e.getCurrentItem().equals(new DeliveryButtons().getVIPPlusItem(player))) {
            boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"vipPlus");
            if (!isReceive) {
                if (player.hasPermission("group.vip+")) {
                    Main.getInstance().getData().updateDeliveryData(uuid, "vipPlus", true);
                    player.sendMessage(ColorUtil.color("§a你领取了这个奖励！"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.init(player);
                    GadgetsAPI.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_3, System.currentTimeMillis() + 86400000L, true, null, 5);
                    playDeliveryEffects(player);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    player.sendMessage(ColorUtil.color("&c你的会员等级不足以领取该奖励！请在&b货摊&c购买会员"));
                    player.closeInventory();
                }
            }
        }
        if (e.getCurrentItem().equals(new DeliveryButtons().getMVPItem(player))) {
            boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"mvp");
            if (!isReceive) {
                if (player.hasPermission("group.mvp")) {
                    Main.getInstance().getData().updateDeliveryData(uuid, "mvp", true);
                    player.sendMessage(ColorUtil.color("§a你领取了这个奖励！"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.init(player);
                    GadgetsAPI.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_4, System.currentTimeMillis() + 86400000L, true, null, 5);
                    playDeliveryEffects(player);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    player.sendMessage(ColorUtil.color("&c你的会员等级不足以领取该奖励！请在&b货摊&c购买会员"));
                    player.closeInventory();
                }
            }
        }
        if (e.getCurrentItem().equals(new DeliveryButtons().getMVPPlusItem(player))) {
            boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"mvpPlus");
            if (!isReceive) {
                if (player.hasPermission("group.mvp+")) {
                    Main.getInstance().getData().updateDeliveryData(uuid, "mvpPlus", true);
                    player.sendMessage(ColorUtil.color("§a你领取了这个奖励！"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.init(player);
                    GadgetsAPI.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_5, System.currentTimeMillis() + 86400000L, true, null, 5);
                    playDeliveryEffects(player);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    player.sendMessage(ColorUtil.color("&c你的会员等级不足以领取该奖励！请在&b货摊&c购买会员"));
                    player.closeInventory();
                }
            }
        }
        if (e.getCurrentItem().equals(new DeliveryButtons().getDailyItem(player))) {
            boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"daily");
            if (!isReceive) {
                Main.getInstance().getData().updateDeliveryData(uuid, "daily", true);
                player.sendMessage(ColorUtil.color("&a你领取了免费的§32,500YumeGames大厅经验&a以及§b5个一星神秘箱！"));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                this.init(player);
                AlonsoLevelsAPI.addExperience(player.getUniqueId(), 2500);
                GadgetsAPI.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_1, System.currentTimeMillis() + 86400000L, true, null, 5);
            }
        }
        if (e.getCurrentItem().equals(new DeliveryButtons().getDoneBoxItem(player)) || e.getCurrentItem().equals(new DeliveryButtons().getDoneDailyItem(player))) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            player.sendMessage(ColorUtil.color("&c你已经领取了这个奖励，请稍后再来！"));
            player.closeInventory();
        }
    }

    public void playDeliveryEffects(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 1.0f), 10L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 1.0f), 15L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 1.0f), 20L);
        ArrayList<Item> items = new ArrayList<Item>();
        Item item = player.getWorld().dropItem(new DeliveryNPC().getNpcSpawnLocation(), new ItemStack(Material.DIAMOND));
        item.setMetadata("TheDeliveryItem", new FixedMetadataValue(Main.getInstance(), true));
        item.setPickupDelay(Integer.MAX_VALUE);
        items.add(item);
        Location effectLocation = new DeliveryNPC().getNpcSpawnLocation().add(0,2.8,0);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            Item item1 = player.getWorld().dropItem(effectLocation, new ItemStack(Material.EMERALD));
            item1.setMetadata("TheDeliveryItem", new FixedMetadataValue(Main.getInstance(), true));
            item1.setPickupDelay(Integer.MAX_VALUE);
            items.add(item1);
        }, 10L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            Item item1 = player.getWorld().dropItem(effectLocation, new ItemStack(Material.DIAMOND));
            item1.setMetadata("TheDeliveryItem", new FixedMetadataValue(Main.getInstance(), true));
            item1.setPickupDelay(Integer.MAX_VALUE);
            items.add(item1);
        }, 15L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            Item item1 = player.getWorld().dropItem(effectLocation, new ItemStack(Material.DIAMOND));
            item1.setMetadata("TheDeliveryItem", new FixedMetadataValue(Main.getInstance(), true));
            item1.setPickupDelay(Integer.MAX_VALUE);
            items.add(item1);
        }, 20L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            for (Item e : items) {
                e.remove();
            }
        }, 200L);
    }

}

