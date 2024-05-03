package cn.starry.hub.functions.achievement.type.lobby;

import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.functions.achievement.AbstractAchievement;
import cn.starry.hub.functions.achievement.AchievementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class LearnToSpeak extends AbstractAchievement implements Listener {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "让世界听到你的声音！";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7第一次聊天");
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
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (PlayerData.PROFILE.get(player).equals(ProfileState.LOADED)) {
            AchievementManager.unlockAchievement(player, this);
        }
    }
}
