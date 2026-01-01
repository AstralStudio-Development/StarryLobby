package cn.starry.hub.listener;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.Permission;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.NickUtil;
import cn.starry.core.utils.RankUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.menu.selector.SelectorMenu;
import cn.starry.hub.listener.handler.LobbyHandler;
import dev.jnic.annotations.Include;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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

@Include
public class LobbyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        e.setJoinMessage(null);
        LobbyHandler handler = new LobbyHandler();

        CacheData.EDIT.put(player,false);
        CacheData.LAUNCHER.put(player,Boolean.parseBoolean(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_launcher").toLowerCase()));

        handler.firstReset(player);
        handler.drop(player);
        handler.loadMessages(player);
        handler.returnToLobby(player);
        handler.load(player);

        AchievementManager.unlockAchievement(player.getUniqueId(),AchievementManager.getAchievement("FirstJoin"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        e.setQuitMessage(null);
        new LobbyHandler().drop(player);

        CacheData.EDIT.remove(player);
        CacheData.LAUNCHER.remove(player);

        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            //如果玩家是会员
            if (player.hasPermission(Permission.RANK.getNode())) {
                String rank = RankUtil.getFormatRankById(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rank"),player.getUniqueId());
                if (player.hasPermission(Permission.RANK.getNode())) {
                    String message = PlaceholderAPI.setPlaceholders(player, NickUtil.isNicked(player.getUniqueId()) ? "&c  -" + rank + " " + NickUtil.getNickName(player.getUniqueId()) + "&7离开了琉光艺庭" : "&c  -" + rank + " %player_name% &7离开了琉光艺庭");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(CC.translate(message));
                        p.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS,1,1);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (CacheData.EDIT.get(player) == null || CacheData.EDIT.get(player).equals(false)) {
            if (!StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
                e.setCancelled(true);
            } else {
                if (!e.getView().getTitle().contains("编辑") && !e.getView().getTitle().contains("物品布局")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onJoinFly(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            if (p.hasPermission(Permission.RANK.getNode())) {
                p.setAllowFlight(true);
                p.setFlying(true);
            }
        }
    }

    @EventHandler
    public void onJoinSpeed(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.setWalkSpeed(Float.parseFloat(String.valueOf(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"speed")))  * 0.1f);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (!(e.getEntity() instanceof FishHook)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWaterChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }
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
        if (event.getEntityType() == EntityType.TNT || event.getEntityType() == EntityType.TNT_MINECART || event.getEntity() instanceof Creeper) {
            event.blockList().clear();
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void Join(PlayerMoveEvent playerMoveEvent) {
        Player player = playerMoveEvent.getPlayer();
        if (playerMoveEvent.getFrom().getBlock().getType() == Material.LEGACY_PORTAL) {
            player.setVelocity(player.getEyeLocation().getDirection().multiply(2));
            AchievementManager.unlockAchievement(player.getUniqueId(),AchievementManager.getAchievement("ThinkingWithPortals"));
            new SelectorMenu().openMenu(player);
        }
    }

}
