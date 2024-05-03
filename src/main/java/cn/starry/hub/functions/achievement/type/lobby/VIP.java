package cn.starry.hub.functions.achievement.type.lobby;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;
import cn.starry.hub.functions.achievement.AchievementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class VIP extends AbstractAchievement implements Listener {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "钞能力";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7成为服务器的会员");
        return description;
    }

    @Override
    public AchievementType getType() {
        return AchievementType.GENERAL;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public int getPoints() {
        return 5;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("group.vip")) {
            AchievementManager.unlockAchievement(player,AchievementManager.getAchievement("VIP"));
        }
    }

}
