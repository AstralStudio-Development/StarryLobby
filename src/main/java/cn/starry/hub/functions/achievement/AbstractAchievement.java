package cn.starry.hub.functions.achievement;

import cn.starry.hub.api.enums.AchievementType;

import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date: 2023/12/19
 */
public abstract class AbstractAchievement {

    public abstract String getInternalName();

    public abstract String getDisplayName();

    public abstract List<String> getDescription();

    public abstract AchievementType getType();

    public abstract boolean isHidden();

    public abstract boolean isPremium();

    public abstract int getPoints();

}
