package cn.starry.hub.functions.activity.springfestival.Y2024.presents;

import cn.starry.hub.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PresentsListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.SKULL)) {
            for (AbstractPresents present : Main.getInstance().getPresentsFactory().getPresents()) {
                if (event.getClickedBlock().getLocation().equals(present.getLocation())) {
                    PresentsManager.unlockPresents(player.getUniqueId(),present);
                }
            }
        }
    }

}
