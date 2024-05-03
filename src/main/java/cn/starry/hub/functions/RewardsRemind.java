package cn.starry.hub.functions;

import cn.starry.hub.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RewardsRemind {

    public void sendMsg(Player player) {
        if (Main.getInstance().rewards) {
            new BukkitRunnable() {
                public void run() {
                    if (!PlaceholderAPI.setPlaceholders(player, "%alonsolevels_available_normal_rewards%").equalsIgnoreCase("0")) {
                        TextComponent tc = new TextComponent("§a你有§b" + PlaceholderAPI.setPlaceholders(player, "%alonsolevels_available_normal_rewards%") + "§a个等级奖励暂未领取！");
                        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7点击打开§3YumeGames等级&e菜单").create()));
                        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rewards"));
                        TextComponent tc2 = new TextComponent("§e点击查看！");
                        tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7点击打开§3YumeGames等级&e菜单").create()));
                        tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rewards"));
                        player.spigot().sendMessage(tc);
                        player.spigot().sendMessage(tc2);
                    }
                }
            }.runTaskLaterAsynchronously(Main.getInstance(), 40L);
        }
    }
}
