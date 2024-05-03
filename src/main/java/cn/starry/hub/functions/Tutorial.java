package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.listener.handler.LobbyHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

@AutoRegister
public class Tutorial implements Listener {

    public void onTutorial(Player player){
        player.getInventory().clear();
        PlayerData.TUTORIAL.put(player,1);
        player.sendTitle("§b右键点击NPC", "§f加入游戏并开始游玩");
        player.teleport(new Location(player.getWorld(), -15.5, 71.0, -0.5, 90, 0));
        player.setAllowFlight(true);
        player.setFlying(true);
        player.sendMessage("");
        player.sendMessage("§b右键点击NPC");
        player.sendMessage("§f加入游戏并开始游玩");
        player.sendMessage("");
        new BukkitRunnable(){

            public void run() {
                PlayerData.TUTORIAL.put(player,2);
                player.sendMessage("§b你可以使用/f add命令");
                player.sendMessage("§f来添加好友");
                player.sendMessage("");
                player.sendTitle("§b你可以使用/f add命令", "§f来邀请添加好友");
                player.teleport(new Location(player.getWorld(), -30.5, 104.0, 60.0, 60, 0));
                player.setFlying(true);
                new BukkitRunnable(){

                    public void run() {
                        PlayerData.TUTORIAL.put(player,3);
                        player.sendTitle("§b你可以使用/p invite命令", "§f来邀请玩家加入你的组队");
                        player.teleport(new Location(player.getWorld(), -108.5, 150.0, -0.5, 90, 0));
                        player.sendMessage("§b你可以使用/p invite命令");
                        player.sendMessage("§f来邀请玩家加入你的组队");
                        player.sendMessage("");
                        player.setFlying(true);
                        new BukkitRunnable(){

                            public void run() {
                                PlayerData.TUTORIAL.put(player,4);
                                player.sendTitle("§b加入我们的交流群", "§f677371713");
                                player.teleport(new Location(player.getWorld(), 149.5, 49.0, -23.5, -180, 0));
                                player.setAllowFlight(true);
                                player.setFlying(true);
                                player.sendMessage("§b加入我们的交流群：677371713");
                                player.sendMessage("§f来与更多玩家交流");
                                player.sendMessage("");
                                new BukkitRunnable(){

                                    public void run() {
                                        PlayerData.TUTORIAL.put(player,5);
                                        player.sendTitle("§b就是这样！", "§f祝你在服务器上玩的开心！");
                                        player.teleport(new Location(player.getWorld(), 0.5, 73.0, -0.5, 90, 0));
                                        player.setAllowFlight(true);
                                        player.setFlying(true);
                                        player.sendMessage("§b就是这样！");
                                        player.sendMessage("§f祝你在服务器上玩的开心！");
                                        player.sendMessage("");
                                        new BukkitRunnable(){

                                            public void run() {
                                                PlayerData.TUTORIAL.put(player,0);
                                                new LobbyHandler().loadItem(player);
                                                if (player.hasPermission("lobby.fly")) {
                                                    player.setAllowFlight(true);
                                                    player.setFlying(false);
                                                    player.setFlySpeed(0.1f);
                                                } else {
                                                    player.setFlying(false);
                                                }
                                            }
                                        }.runTaskLater(Main.getInstance(), 20L);
                                    }
                                }.runTaskLater(Main.getInstance(), 80L);
                            }
                        }.runTaskLater(Main.getInstance(), 80L);
                    }
                }.runTaskLater(Main.getInstance(), 80L);
            }
        }.runTaskLater(Main.getInstance(), 80L);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (PlayerData.TUTORIAL.get(player).equals(1)) {
            player.setFlying(true);
            player.teleport(new Location(player.getWorld(), -15.5, 71.0, -0.5, 90, 0));
        } else if (PlayerData.TUTORIAL.get(player).equals(2)) {
            player.teleport(new Location(player.getWorld(), -30.5, 104.0, 60.0, 60, 0));
        } else if (PlayerData.TUTORIAL.get(player).equals(3)) {
            player.teleport(new Location(player.getWorld(), -108.5, 150.0, -0.5, 90, 0));
        } else if (PlayerData.TUTORIAL.get(player).equals(4)) {
            player.teleport(new Location(player.getWorld(), 156.5, 60.0, -56.5, -180, 0));
        } else if (PlayerData.TUTORIAL.get(player).equals(5)) {
            player.teleport(new Location(player.getWorld(), 0.5, 53.0, -0.5, 90, 0));
        }
    }

}
