package cn.starry.hub.listener;

import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.Main;
import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.listener.handler.LobbyHandler;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.VanishUtil;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Objects;
import java.util.UUID;

@AutoRegister
public class LobbyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        e.setJoinMessage(null);
        LobbyHandler handler = new LobbyHandler();
        handler.firstReset(player);
        handler.drop(player);
        handler.loadMessages(player);
        handler.returnToLobby(player);
        handler.load(player);
        //如果玩家是会员 (此处代码已被移至new LobbyHandler())
        VanishUtil.checkVanishState(player,false,true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        e.setQuitMessage(null);
        new LobbyHandler().drop(player);
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            //如果玩家是会员
            if (player.hasPermission("lobby.rankleave")) {
                //String message = PlaceholderAPI.setPlaceholders(player, "&c- %luckperms_prefix%%player_name% &7消散在了仙境之中");
                //for (Player p : Bukkit.getOnlinePlayers()) {
                    //p.sendMessage(ColorUtil.color(message));
                    //p.playSound(player.getLocation(), Sound.NOTE_BASS,1,1);
                //}
            }
        }
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (PlayerData.EDIT.get(player) == PlayerState.EDIT_OFF) {
            if (!Main.getInstance().getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
                e.setCancelled(true);
            } else {
                if (!e.getInventory().getName().contains("编辑") && !e.getInventory().getName().contains("物品布局")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onJoinFly(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            if (p.hasPermission("lobby.fly")) {
                p.setAllowFlight(true);
                p.setFlying(true);
            }
        }
    }

    @EventHandler
    public void onJoinSpeed(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        player.setWalkSpeed(Float.parseFloat(Main.getInstance().getData().getPlayerData(uuid,"speed")) * 0.1f);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWaterChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void AntiAchievement(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    /*
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Player) {
            Player clickedPlayer = (Player) event.getRightClicked();
            if (clickedPlayer != null) {
                new InteractiveMenu().openMenu(player,clickedPlayer);
            }
        }
    }

     */

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity.getType() == EntityType.ARMOR_STAND) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        Entity entity = e.getEntity();
        if (!(entity.getType() == EntityType.ARMOR_STAND)) {
            e.setCancelled(true);
        }
    }

    //YumeGames
    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT || event.getEntity() instanceof Creeper) {
            event.blockList().clear();
            event.setCancelled(true);
        }
    }

}
