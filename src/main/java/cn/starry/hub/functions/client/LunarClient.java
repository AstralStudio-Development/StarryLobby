package cn.starry.hub.functions.client;

import cn.starry.hub.Main;
import cn.starry.hub.functions.achievement.AchievementManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.messaging.Messenger;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LunarClient implements Listener {

    private static String prefix = "&b☾ ";

    private static Set<UUID> playersRunningLunarClient = Collections.newSetFromMap(new ConcurrentHashMap());

    public void initClient() {
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(Main.getInstance(), "lunarclient:pm");
        messenger.registerIncomingPluginChannel(Main.getInstance(), "lunarclient:pm", (channel, player, bytes) -> {
        });
    }

    @EventHandler
    public void onRegister(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equals("lunarclient:pm")) {
            return;
        }
        //event.getPlayer().sendMessage(ColorUtil.color("&f你正在使用&bLunar Client&f游玩&b仙境&f服务器"));
        AchievementManager.unlockAchievement(event.getPlayer(),AchievementManager.getAchievement("onLunar"));
        playersRunningLunarClient.add(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onUnregister(PlayerUnregisterChannelEvent event) {
        if (event.getChannel().equals("lunarclient:pm")) {
            playersRunningLunarClient.remove(event.getPlayer().getUniqueId());
        }
    }

    public static boolean isRunningLunar(UUID uuid) {
        return playersRunningLunarClient.contains(uuid);
    }

    public static String getPrefix(UUID uuid) {
        return isRunningLunar(uuid) ? prefix : "";
    }

}
