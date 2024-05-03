package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.parm.AutoRegister;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;

import java.util.UUID;

@AutoRegister
public class DatabaseListeners implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Main.getInstance().getData().savePlayerData(playerUUID, player.getName(),"false", "ENABLE", "ENABLE", "DAY","ENABLE","ENABLE","1","ALL","2","false","false","","false","CHINESE","false","Steve","NOP_DEFAULT","RED","GOLD","0");
        Main.getInstance().getData().saveSlotData(playerUUID, player.getName(), "NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE");
        Main.getInstance().getData().saveAchievementData(playerUUID, player.getName(), "", 0);
        Main.getInstance().getData().saveDeliveryData(playerUUID, player.getName(), false, false, false, false, false, false, false);
        Main.getInstance().getData().savePresentsData(playerUUID, player.getName(), "", 0);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onStartUp(PluginEnableEvent event) {
        Main.getInstance().getData().saveBoosterData("", "BEDWARS",1);
        Main.getInstance().getData().saveBoosterData("", "THEPIT",1);
        Main.getInstance().getData().saveBoosterData("", "DUEL",1);
        Main.getInstance().getData().saveBoosterData("", "SKYWARS",1);
        Main.getInstance().getData().saveBoosterData("", "ARCADE",1);
        Main.getInstance().getData().saveBoosterData("", "MURDERMYSTERY",1);
        Main.getInstance().getData().saveBoosterData("", "SKYBLOCK",1);
        Main.getInstance().getData().saveBoosterData("", "HOUSING",1);
        Main.getInstance().getData().saveBoosterData("", "BUILDBATTLE",1);
        Main.getInstance().getData().saveBoosterData("", "UHC",1);
        Main.getInstance().getData().saveBoosterData("", "MEGAWALLS",1);
    }

}
