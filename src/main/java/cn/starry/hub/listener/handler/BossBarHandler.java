package cn.starry.hub.listener.handler;

import cn.starry.hub.Main;
import cn.starry.hub.utils.BossBarUtil;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarHandler implements Listener {
    BossBarUtil bossBar = new BossBarUtil(Main.getInstance(), "");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (this.bossBar.containsPlayer(player)) {
            this.bossBar.removePlayer(player);
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), () -> this.bossBar.addPlayer(player), 1L);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), () -> this.bossBar.removePlayer(player), 1L);
        if (this.bossBar.containsPlayer(player)) {
            this.bossBar.removePlayer(player);
        }
    }

    public void init() {
        new BukkitRunnable() {
            int tick = 0;
            @Override
            public void run() {
                if (tick >= 28) {
                    tick = 0;
                }
                String start;
                if (tick == 1) {
                    start = "&6&l起床战争 漏洞补丁推送！";
                } else if (tick == 2) {
                    start = "&e&l起床战争 漏洞补丁推送！";
                } else if (tick == 3) {
                    start = "&6&l起床战争 漏洞补丁推送！";
                } else if (tick == 4) {
                    start = "&e&l起床战争 漏洞补丁推送！";
                } else if (tick == 5) {
                    start = "&6&l起床战争 漏洞补丁推送！";
                } else if (tick == 6) {
                    start = "&e&l起床战争 漏洞补丁推送！";
                } else if (tick == 7) {
                    start = "&6&l起床战争 漏洞补丁推送！";
                } else if (tick == 8) {
                    start = "&e&l起床战争 漏洞补丁推送！";
                } else if (tick == 9) {
                    start = "&6&l起床战争 漏洞补丁推送！";
                } else if (tick == 10) {
                    start = "&e&l起床战争 漏洞补丁推送！";
                } else if (tick == 11) {
                    start = "&a&l天坑乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 12) {
                    start = "&6&l天&a&l坑乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 13) {
                    start = "&2&l天&6&l坑&a&l乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 14) {
                    start = "&2&l天坑&6&l乱&a&l斗崩溃修复 新附魔!新事件!";
                } else if (tick == 15) {
                    start = "&2&l天坑乱&6&l斗&a&l崩溃修复 新附魔!新事件!";
                } else if (tick == 16) {
                    start = "&2&l天坑乱斗&6&l崩&a&l溃修复 新附魔!新事件!";
                } else if (tick == 17) {
                    start = "&2&l天坑乱斗崩&6&l溃&a&l修复 新附魔!新事件!";
                } else if (tick == 18) {
                    start = "&2&l天坑乱斗崩溃&6&l修&a&l复 新附魔!新事件!";
                } else if (tick == 19) {
                    start = "&2&l天坑乱斗崩溃修&6&l复&a&l 新附魔!新事件!";
                } else if (tick == 20) {
                    start = "&2&l天坑乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 21) {
                    start = "&2&l天坑乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 22) {
                    start = "&2&l天坑乱斗崩溃修复 &f&l新附魔!新事件!";
                } else if (tick == 23) {
                    start = "&2&l天坑乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 24) {
                    start = "&2&l天坑乱斗崩溃修复 &f&l新附魔!新事件!";
                } else if (tick == 25) {
                    start = "&2&l天坑乱斗崩溃修复 新附魔!新事件!";
                } else if (tick == 26) {
                    start = "&2&l天坑乱斗崩溃修复 &f&l新附魔!新事件!";
                } else if (tick == 27) {
                    start = "&2&l天坑乱斗崩溃修复 新附魔!新事件!";
                } else {
                    start = "&6&l起床战争 漏洞补丁推送！";
                }
                final String title = ColorUtil.color(start);
                bossBar.setTitle(title);
                tick++;
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 10, 3);
    }

}
