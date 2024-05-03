package cn.starry.hub.functions.achievement.type.lobby;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class Hello2024 extends AbstractAchievement {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "你好，2024";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7抵达公元2024年");
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
        return true;
    }

    @Override
    public int getPoints() {
        return 20;
    }

}
