package cn.starry.hub.functions.achievement.type.lobby;

import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;

import java.util.ArrayList;
import java.util.List;

public class ChairCanSit extends AbstractAchievement {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "椅子能坐？！";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7在大厅内坐下");
        return description;
    }

    @Override
    public AchievementType getType() {
        return AchievementType.GENERAL;
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public int getPoints() {
        return 5;
    }

}
