package cn.starry.hub.functions.achievement.type.lobby;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;
import cn.starry.hub.functions.achievement.AchievementManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class PersonInPower extends AbstractAchievement implements Listener {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "实质当权人";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7和一名运营在同一大厅");
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
        return true;
    }

    @Override
    public int getPoints() {
        return 10;
    }

    @EventHandler
    public void onOwnerJoin(PlayerJoinEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission("group.owner")) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    AchievementManager.unlockAchievement(target, AchievementManager.getAchievement("PersonInPower"));
                }
            }
        }
    }

}
