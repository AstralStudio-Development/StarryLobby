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
public class FirstJoin extends AbstractAchievement implements Listener {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "初次见面";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7第一次加入YumeGames服务器");
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
    public void onChat(PlayerJoinEvent event){
        Player player = event.getPlayer();
        AchievementManager.unlockAchievement(player,this);
    }
}
