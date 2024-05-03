package cn.starry.hub.utils;

import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VanishUtil {

    public static void checkVanishState(Player player,boolean isChange,boolean isSwap) {
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"vanish").equalsIgnoreCase("true")) {
            if (!isSwap) {
                if (isChange) {
                    player.sendMessage(ColorUtil.color("&f隐身&c已关闭！"));
                    Main.getInstance().getData().updatePlayerData(player.getUniqueId(), "vanish", "false");
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Main.getInstance().getData().getPlayerData(p.getUniqueId(), "settings_lobby_show").equalsIgnoreCase("ENABLE") || Main.getInstance().getData().getPlayerData(p.getUniqueId(), "settings_lobby_show").equalsIgnoreCase("RANK")) {
                        p.showPlayer(player);
                    }
                }
            } else {
                if (isChange) {
                    player.sendMessage(ColorUtil.color("&f隐身&a已开启！"));
                    Main.getInstance().getData().updatePlayerData(player.getUniqueId(), "vanish", "true");
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.hidePlayer(player);
                    if (isChange) {
                        if (p.hasPermission("lobby.vanish")) {
                            if (p != player) {
                                p.sendMessage(ColorUtil.color("&c" + player.getName() + "&f开启了隐身！"));
                            }
                        }
                    }
                }
            }
        } else {
            if (!isSwap) {
                if (isChange) {
                    player.sendMessage(ColorUtil.color("&f隐身&a已开启！"));
                    Main.getInstance().getData().updatePlayerData(player.getUniqueId(), "vanish", "true");
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.hasPermission("lobby.vanish")) {
                        p.hidePlayer(player);
                    }
                    if (isChange) {
                        if (p.hasPermission("lobby.vanish")) {
                            p.sendMessage(ColorUtil.color("&c" + player.getName() + "&f开启了隐身！"));
                        }
                    }
                }
            } else {
                if (isChange) {
                    player.sendMessage(ColorUtil.color("&f隐身&c已关闭！"));
                    Main.getInstance().getData().updatePlayerData(player.getUniqueId(), "vanish", "false");
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Main.getInstance().getData().getPlayerData(p.getUniqueId(), "settings_lobby_show").equalsIgnoreCase("ENABLE") || Main.getInstance().getData().getPlayerData(p.getUniqueId(), "settings_lobby_show").equalsIgnoreCase("RANK")) {
                        p.showPlayer(player);
                    }
                }
            }
        }
        sendVanishActionbar(player);
    }

    public static void sendVanishActionbar(Player player) {
        new BukkitRunnable(){
            public void run() {
                if (!Main.getInstance().getConfig().getBoolean("apiMode")) {
                    if (Main.getInstance().getData().getPlayerData(player.getUniqueId(), "vanish").equalsIgnoreCase("true") && NickUtil.isNicked(player.getUniqueId())) {
                        ActionBarUtil.sendActionbar(player, ColorUtil.color("&f你目前处于&c隐身&f,&c匿名&f状态"));
                    } else if (Main.getInstance().getData().getPlayerData(player.getUniqueId(), "vanish").equalsIgnoreCase("true")) {
                        ActionBarUtil.sendActionbar(player, ColorUtil.color("&f你目前处于&c隐身&f状态"));
                    } else if (NickUtil.isNicked(player.getUniqueId())) {
                        ActionBarUtil.sendActionbar(player, ColorUtil.color("&f你目前处于&c匿名&f状态"));
                    } else {
                        cancel();
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0L, 3L);
    }

    public static boolean isVanish(Player player) {
        return Boolean.parseBoolean(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"vanish"));
    }

}
