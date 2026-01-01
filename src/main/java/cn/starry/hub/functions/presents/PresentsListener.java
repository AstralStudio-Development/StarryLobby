package cn.starry.hub.functions.presents;

import cn.starry.hub.StarryLobby;
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
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.LEGACY_SKULL_ITEM)) {
            for (AbstractPresents present : StarryLobby.getInstance().getPresentsFactory().getPresents()) {
                if (event.getClickedBlock().getLocation().equals(present.getLocation())) {
                    PresentsManager.unlockPresents(player.getUniqueId(),present);
                }
            }
        }
    }

}
