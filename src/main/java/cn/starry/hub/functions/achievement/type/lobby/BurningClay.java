package cn.starry.hub.functions.achievement.type.lobby;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;
import cn.starry.hub.functions.achievement.AchievementManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class BurningClay extends AbstractAchievement implements Listener {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "永焱燃土";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7于11月7日在聊天栏中发送BurningClay");
        description.add("&7&o纪念逝去的日子");
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
        return 15;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().contains("BurningClay")) {
            LocalDate currentDate = LocalDate.now();
            boolean isNovember7 = (currentDate.getMonthValue() == 11 && currentDate.getDayOfMonth() == 7);
            if (isNovember7) {
                AchievementManager.unlockAchievement(event.getPlayer(), this);
            }
        }
    }

}
