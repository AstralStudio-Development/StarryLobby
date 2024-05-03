package cn.starry.hub.functions.achievement.type.pit;

import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;

import java.util.ArrayList;
import java.util.List;

public class SuperLightning extends AbstractAchievement {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "超级闪电";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7被Kaboom闪电击飞");
        return description;
    }

    @Override
    public AchievementType getType() {
        return AchievementType.PIT;
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
        return 5;
    }

}
