package cn.starry.hub.functions;

import cn.starry.hub.parm.AutoRegister;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@AutoRegister
public class ProtectPlugin implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        e.setMessage(e.getMessage().replaceAll("/minecraft:tp", "/tp"));
    }

}
